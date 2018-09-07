package phptravels.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import phptravels.driver.Driver;

import java.util.List;
import java.util.Optional;

public class DashboardPage {

    public static boolean isAt() {
        List<WebElement> h5s = Driver.instance.findElements(By.className("h5"));

        return h5s.stream()
                .anyMatch(h5 -> h5.getText().contains("QUICK BOOKING"));
    }

    public enum TopNavigationBar {
        QUICK_BOOKING("QUICK BOOKING"),
        BOOKINGS("BOOKINGS");

        String menuButtonText;

        TopNavigationBar(String menuButtonText) {
            this.menuButtonText = menuButtonText;
        }

        public void click() {
            List<WebElement> h5s = Driver.instance.findElements(By.className("h5"));

            Optional<WebElement> matchingElement = h5s.stream()
                    .filter(h5 -> h5.getText().trim().equalsIgnoreCase(menuButtonText))
                    .findFirst();

            matchingElement.orElseThrow(() ->
                    new ElementNotInteractableException("Button in top navigation bar could not be found"))
                    .click();
        }
    }
}
