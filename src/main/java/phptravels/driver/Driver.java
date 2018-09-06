package phptravels.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver instance;

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\repos\\phptravels\\src\\main\\resources\\drivers\\chromedriver.exe");
    }

    public static void initialize() {
        instance = new ChromeDriver();
        instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void destroy() {
        instance.manage().deleteAllCookies();
        instance.close();
        instance.quit();
    }
}
