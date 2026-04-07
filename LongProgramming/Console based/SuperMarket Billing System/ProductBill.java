public class ProductBill {
    private int billId;
    private int customerId;
    private int productId;
    private int quantity;
    private double totalamount;
    private String paymentmethod;
    private String billdate;
    ProductBill(  int billId,int customerId,int productId,int quantity,double totalamount,String paymentmethod,String billdate){
        this.billId=billId;
        this.customerId=customerId;
        this.productId=productId;
        this.quantity=quantity;
        this.totalamount= totalamount;
        this.paymentmethod=paymentmethod;
        this.billdate=billdate;
    }

    public int getbillId() {
        return billId;
    }

    public void setbillId(int billId) {
        this.billId = billId;
    }

    public int getcustomerId() {
        return customerId;
    }

    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public double gettotalamount() {
        return totalamount;
    }

    public void settotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public String getpaymentmethod() {
        return paymentmethod;
    }

    public void setpaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getbilldate() {
        return billdate;
    }

    public void setbilldate(String billdate) {
        this.billdate = billdate;
    }
}
