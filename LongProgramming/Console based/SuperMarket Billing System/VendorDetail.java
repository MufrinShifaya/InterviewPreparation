public class VendorDetail {
    private int vendorId;
    private String vendorname;
    private String login;
    private String logout;
    VendorDetail( int vendorId,String Vendorname,String login,String logout){
        this.vendorId=vendorId;
        this.vendorname=vendorname;
        this.login=login;
        this.logout=logout;
    }

    public int getvendorId() {
        return vendorId;
    }

    public void setvendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getvendorname() {
        return vendorname;
    }

    public void setvendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getlogout() {
        return logout;
    }

    public void setlogout(String logout) {
        this.logout = logout;
    }
}
