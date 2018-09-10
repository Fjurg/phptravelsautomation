package phptravels.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import phptravels.driver.Driver;
import phptravels.enums.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class QuickBookingPage {

    public static QuickBookingCommand createQuickBooking() {
        return new QuickBookingCommand();
    }

    public static class QuickBookingCommand {

        private Service service;
        private int daysToStay;
        private Hotel hotel;
        private RoomType roomType;
        private PaymentMethod paymentMethod;
        private Extra extra;

        public QuickBookingCommand withService(Service service) {
            this.service = service;
            return this;
        }

        public QuickBookingCommand withDurationToStayInDays(int daysToStay) {
            this.daysToStay = daysToStay;
            return this;
        }

        public QuickBookingCommand withHotelName(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public QuickBookingCommand withRoomType(RoomType roomType) {
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

            Driver.waitFor(Driver.instance.findElement(By.id("servicetype")));

            Select dropdown = new Select(Driver.instance.findElement(By.id("servicetype")));
            dropdown.selectByVisibleText(service.getHotels());

            getOptionByTagName("button", "NEXT").click();

//            List<WebElement> buttons = Driver.instance.findElements(By.tagName("button"));
//
//            Optional<WebElement> nextButton = buttons.stream()
//                    .filter(button -> button.getText().contains("NEXT"))
//                    .findFirst();
//
//            nextButton.orElseThrow(() -> new ElementNotSelectableException("Next-button could not be found"))
//                    .click();

            // Enter date
            Driver.instance.findElement(By.className("form-control dpd1")).sendKeys(getPreferredDayToCheckOut(0));
            Driver.instance.findElement(By.className("form-control dpd2")).sendKeys(getPreferredDayToCheckOut(daysToStay));

            // Select hotel
            Driver.instance.findElement(By.id("s2id_autogen3")).sendKeys(hotel.getHotelName() + Keys.ENTER);

            // Select room
            Driver.instance.findElement(By.id("s2id_poprooms")).sendKeys(roomType.getRoomType() + Keys.ENTER);

            // Select Payment method
            Driver.instance.findElement(By.linkText("paymentmethod")).click();
            getOptionByTagName("option", paymentMethod.getPaymentMethod()).click();

            // Click book now-button
            Driver.instance.findElement(By.className("btn btn-primary btn-lg")).click();
        }

        private WebElement getOptionByTagName(String tagName, String option) {
            List<WebElement> elements = Driver.instance.findElements(By.tagName(tagName));
            Optional<WebElement> matchingOption = elements.stream()
                    .filter(e -> e.getText().equalsIgnoreCase(option))
                    .findFirst();

            return matchingOption.orElseThrow(() ->
                    new ElementNotSelectableException("Could not locate option: " + option));
        }

        private String getPreferredDayToCheckOut(int daysToStay) {
            return LocalDateTime.now()
                    .plusDays(daysToStay)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }
}
