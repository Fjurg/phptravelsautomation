package phptravels.enums;

public enum Service {
    HOTELS("Hotels");

    private String hotels;

    Service(String hotels) {
        this.hotels = hotels;
    }

    public String getHotels() {
        return hotels;
    }
}
