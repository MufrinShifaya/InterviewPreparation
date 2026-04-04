public class Booking {
    private int bookingId;
    private String vehicleId;
    private User user;
    private Driver driver;

    public Booking(int bookingId, String vehicleId, User user, Driver driver) {
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.user = user;
        this.driver = driver;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }
}