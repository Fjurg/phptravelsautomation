package phptravels.page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import phptravels.driver.Driver;
import phptravels.enums.*;

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

        public void book() {

            //TODO: How can i click this fucking dropdown?
            Select dropDown = new Select(Driver.instance.findElement(By.id("servicetype")));

            Driver.instance.findElement(By.id("servicetype")).click();

            dropDown.selectByVisibleText(service.getHotels());

            Driver.instance.findElement(By.className("btn btn-primary")).click();
        }
    }
}
