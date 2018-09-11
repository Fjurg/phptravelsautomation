package phptravels.page;

import org.awaitility.Awaitility;
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
import java.util.concurrent.TimeUnit;

public class QuickBookingPage {

    public static boolean isAt() {
        return true;
    }

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

            getElementByTagAndText("button", "next").click();

            inputCheckInDate();
            inputCheckOutDate();

            inputHotel();

            // Todo: LOL Refactor this. It works though.
            Awaitility.await().ignoreException(ElementNotSelectableException.class)
                    .pollInterval(500, TimeUnit.MILLISECONDS)
                    .until(() -> {
                        List<WebElement> span1 = Driver.instance.findElements(By.tagName("span"));
                        return span1.stream()
                                .anyMatch(span -> span.getText().equalsIgnoreCase("select room"));
                    });

            // Select room
            WebElement selectRoomDropdown = Driver.instance.findElement(By.id("s2id_poprooms"));
            selectRoomDropdown.click();
            WebElement searchForRoomInput = getElementByTagAndAttribute("input", "class", "select2-input select2-focused");
            searchForRoomInput.sendKeys(roomType.getRoomType() + Keys.ENTER);

            // Select Payment method
            Driver.instance.findElement(By.linkText("paymentmethod")).click();
            getElementByTagAndText("option", paymentMethod.getPaymentMethod()).click();

            // Click book now-button
            Driver.instance.findElement(By.className("btn btn-primary btn-lg")).click();
        }

        private void inputHotel() {
            WebElement selectHotelDropdown = Driver.instance.findElement(By.id("s2id_autogen3"));
            selectHotelDropdown.click();
            WebElement searchForHotelsInput = getElementByTagAndAttribute("input", "class", "select2-input select2-focused");
            searchForHotelsInput.sendKeys(hotel.getHotelName() + Keys.ENTER);
        }

        private void inputCheckOutDate() {
            WebElement checkoutInput = getElementByTagAndAttribute("input", "class", "form-control dpd2");
            checkoutInput.sendKeys(getTodayPlus(daysToStay));
            checkoutInput.click();
        }

        private void inputCheckInDate() {
            WebElement checkinInput = getElementByTagAndAttribute("input", "id", "HoTels");
            checkinInput.sendKeys(getTodayPlus(0));
            checkinInput.click();
        }

        private WebElement getElementByTagAndAttribute(String tagName, String attribute, String attributeValue) {
            List<WebElement> elements = Driver.instance.findElements(By.tagName(tagName));
            Optional<WebElement> element = elements.stream()
                    .filter(tag -> tag.getAttribute(attribute).equalsIgnoreCase(attributeValue))
                    .findFirst();

            return element.orElseThrow(() ->
                    new ElementNotSelectableException("Could not locate attribute " + attribute + " with value " + attributeValue));
        }

        private WebElement getElementByTagAndText(String tagName, String element) {
            Driver.waitFor(Driver.instance.findElements(By.tagName(tagName)).get(0));
            List<WebElement> elements = Driver.instance.findElements(By.tagName(tagName));
            Optional<WebElement> matchingOption = elements.stream()
                    .filter(e -> e.getText().equalsIgnoreCase(element))
                    .findFirst();

            return matchingOption.orElseThrow(() ->
                    new ElementNotSelectableException("Could not locate element with text: " + element));
        }

        private String getTodayPlus(int numberOfDays) {
            return LocalDateTime.now()
                    .plusDays(numberOfDays)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }
}
