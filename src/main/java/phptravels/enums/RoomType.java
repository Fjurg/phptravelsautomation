package phptravels.enums;

public enum RoomType {
    DELUX_ROOM("Delux Room");

    private final String roomType;

    RoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
