package phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashBoard {

    public static boolean isAt() {
        List<WebElement> h5s = Driver.instance.findElements(By.className("h5"));

        return h5s.stream()
                .anyMatch(h5 -> h5.getText().contains("QUICK BOOKING"));
    }

}
