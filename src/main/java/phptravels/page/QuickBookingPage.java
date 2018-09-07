package phptravels.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import phptravels.driver.Driver;
import phptravels.enums.*;

import java.util.List;
import java.util.Optional;

public class QuickBookingPage {

    public static QuickBookingCommand createQuickBooking() {
        return new QuickBookingCommand();
    }

    public static class QuickBookingCommand {

        private Service service;
        private int duration;
        private Hotel hotel;
        private Room roomType;
        private PaymentMethod paymentMethod;
        private Extra extra;

        public QuickBookingCommand withService(Service service) {
            this.service = service;
            return this;
        }

        public QuickBookingCommand withDurationToStayInDays(int duration) {
            this.duration = duration;
            return this;
        }

        public QuickBookingCommand withHotelName(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public QuickBookingCommand withRoomName(Room roomType) {
            this.roomType = roomType;
            return this;
        }

        public QuickBookingCommand withPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public QuickBookingCommand withExtras(Extra extra) {
            this.extra = extra;
            return this;
        }

        public void book() throws Exception {
            Driver.instance.switchTo().activeElement();

            Driver.waitFor();
            Select dropdown = new Select(Driver.instance.findElement(By.id("servicetype")));

            dropdown.selectByVisibleText(service.getHotels());

            List<WebElement> buttons = Driver.instance.findElements(By.tagName("button"));

            Optional<WebElement> nextButton = buttons.stream()
                    .filter(button -> button.getText().contains("NEXT"))
                    .findFirst();

            nextButton.orElseThrow(() -> new ElementNotSelectableException("Next-button could not be found"))
                    .click();
        }
    }
}
