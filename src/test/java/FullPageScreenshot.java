import org.junit.Test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.List;

public class FullPageScreenshot {

    @Test
    public void test1() throws  Exception {

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://about.fb.com/news/");
        Thread.sleep(2000);
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(),"PNG",new File("/Users/a.kuznetsov/Desktop/1.jpg"));
        driver.quit();
    }
}
