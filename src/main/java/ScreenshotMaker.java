import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScreenshotMaker {

    private int index;
    private ChromeDriver driver;
    private FolderManager folderManager;

    public ScreenshotMaker(ChromeDriver driver, FolderManager folderManager) {
        this.driver = driver;
        this.folderManager = folderManager;
        this.index = 0;
    }

    public ScreenshotMaker openUrl(String url, int timeOut) {
        index++;
        System.out.println(index + ". Take screenshot by: " + url);
        driver.get(url);
        try {
            Thread.sleep(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public ScreenshotMaker removeFixedElements(String[] xPaths) {
        for (String xPath : xPaths) {
            List<WebElement> elements = driver.findElementsByXPath(xPath);
            System.out.println("xPath: " + xPath + ", count: " + elements.size());
            if (elements.size() > 0) {
                for (WebElement element : elements) {
                    String positionValue = element.getCssValue("position");
                    System.out.println("xPath: " + xPath + ", positionValue: " + positionValue);
                    if (xPath.contains("wrapper") || (positionValue.equals("fixed"))) {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].setAttribute('style', 'margin-left:auto')", element);
                        js.executeScript("arguments[0].setAttribute('style', 'margin-right:auto')", element);
                        js.executeScript("arguments[0].setAttribute('style', 'position:static')", element);
                    }
                }
            }
        }
        return this;
    }

    public ScreenshotMaker takeScreenshot(String url) {
        Screenshot fpScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

        File screenshot = new File(folderManager.getScreenshotFolderByUrl(url) + "/" + getScrenshotName(url));
        System.out.println("ScreenshotName: " + screenshot.getAbsolutePath());
        try {
            ImageIO.write(fpScreenshot.getImage(), "PNG", screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private String getScrenshotName(String url) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date();
        return url.split("https://")[1].replace("/", "_") + "_" + dateFormat.format(date) + ".png";
    }
}
