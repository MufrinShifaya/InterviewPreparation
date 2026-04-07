public class ProductDetail {
    private int productId;
    private String productname;
    private double price;
    private int quantity;
    ProductDetail(int productId,String productname,double price){
        this.productId=productId;
        this.productname=productname;
        this.price=price;
        this.quantity=quantity;
    }

    public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }

    public String getproductname() {
        return productname;
    }

    public void setproductname(String productname) {
        this.productname = productname;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }
}
