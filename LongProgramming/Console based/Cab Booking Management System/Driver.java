public class Driver {
    private String drivername;
    private int driverId;
    private long phonenumber;

    public Driver(String drivername, int driverId, long phonenumber) {
        this.drivername = drivername;
        this.driverId = driverId;
        this.phonenumber = phonenumber;
    }

    public String getdrivername() {
        return drivername;
    }

    public void setdrivername(String drivername) {
        this.drivername = drivername;
    }

    public int getdriverId() {
        return driverId;
    }

    public void setdriverId(int driverId) {
        this.driverId = driverId;
    }

    public long getphonenumber() {
        return phonenumber;
    }

    public void setphonenumber(long phoneNumber) {
        this.phonenumber = phoneNumber;
    }
}