
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {

    private static ChromeDriver driver;
    private static FolderManager folderManager;

    public static void main(String[] args) throws Exception {

        folderManager = new FolderManager();

        folderManager
                .createNewScreenshotFolder("UrlScreenshots")
                .createHelpCenterFolder("Help_Center_URLs")
                .createIgSelectedFolder("IG_Selected_URLs");

        List<String> urls = new UrlSourceProvider()
                .setDesktopAsSource()
                .setFileName("UrlSource.txt")
                .setOutputFileName("Filtered Urls.txt")
                .getUrlList();

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1700, 1150));

        ScreenshotMaker screenshotMaker = new ScreenshotMaker(driver, folderManager);

        for (String url : urls) {
            screenshotMaker
                    .openUrl(url, 2000)
                    .removeFixedElements(FixedBanners.xpaths)
                    .takeScreenshot(url);
        }
        driver.quit();
    }
}
