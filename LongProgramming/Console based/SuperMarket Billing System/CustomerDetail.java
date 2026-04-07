public class CustomerDetail {
    private int customerId;
    private String customername;
    private long phonenumber;
    CustomerDetail(int customerId,String customername,long phonenumber){
    this.customerId=customerId;
    this.customername=customername;
    this.phonenumber=phonenumber;
  }

    public int getcustomerId() {
        return customerId;
    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getcustomername() {
        return customername;
    }

    public void setcustomername(String customername) {
        this.customername = customername;
    }

    public long getphonenumber() {
        return phonenumber;
    }

    public void setphonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }
}
