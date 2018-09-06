package phptravels;

import org.openqa.selenium.chrome.ChromeDriver;

public class Class1 extends FunctionalTest {

    public static void go() {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("http://google.se");
    }

}
