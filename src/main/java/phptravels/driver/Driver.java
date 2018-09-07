package phptravels.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver instance;

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\repos\\phptravelsautomation\\src\\main\\resources\\drivers\\chromedriver.exe");
    }

    public static void initialize() {
        instance = new ChromeDriver();
        instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriverWait waitFor() {
        return new WebDriverWait(instance, 10);
    }

    public static void destroy() {
//        instance.manage().deleteAllCookies();
//        instance.close();
//        instance.quit();
    }
}
