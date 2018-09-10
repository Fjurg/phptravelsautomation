package phptravels.enums;

public enum Hotel {
    RENDEZVOUS_HOTELS("Rendezvous Hotels");

    private final String hotel;

    Hotel(String hotel) {
        this.hotel = hotel;
    }

    public String getHotelName() {
        return hotel;
    }
}
