import java.util.*;
class Product {
    int id;
    String name;
    double price;
    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
class BillItem {
    String name;
    int quantity;
    double price;
    double total;
    BillItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }
}
public class Main {
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<BillItem> billList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== USER LOGIN =====");
        System.out.print("Enter Vendorname: ");
        String user = sc.nextLine();
        System.out.println("Login Time: " + new Date());
        System.out.println("\n===== CUSTOMER DETAILS =====");
        System.out.print("Enter Customer Name: ");
        String customer = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        long phone = sc.nextLong();
        addProducts();
        double grandTotal = 0;
        while (true) {
            showProducts();
            System.out.print("\nEnter Product ID (0 to finish): ");
            int id = sc.nextInt();
            if (id == 0)
                break;
            Product selected = null;
            for (Product p : products) {
                if (p.id == id) {
                    selected = p;
                    break;
                }
            }
            if (selected == null) {
                System.out.println("Invalid Product!");
                continue;
            }
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            BillItem item = new BillItem(selected.name, qty, selected.price);
            billList.add(item);
            grandTotal += item.total;
            System.out.println("Added: " + selected.name);
        }
        System.out.println("\n=========== FINAL BILL ===========");
        System.out.println("Customer: " + customer);
        System.out.println("Phone: " + phone);
        System.out.println("---------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s\n", "Item", "Qty", "Price", "Total");

        for (BillItem b : billList) {
            System.out.printf("%-10s %-10d %-10.2f %-10.2f\n",
                    b.name, b.quantity, b.price, b.total);
        }

        System.out.println("---------------------------------");
        System.out.println("Grand Total: " + grandTotal);
        System.out.print("Payment Method (Cash/Card): ");
        sc.next();
        System.out.println("\nPayment Successful!");
        System.out.println("Logout Time: " + new Date());
        System.out.println("\nThank you for purchasing!");
        System.out.println("Visit Again 😊");
    }
    static void addProducts() {
        products.add(new Product(1, "Rice", 60));
        products.add(new Product(2, "Sugar", 40));
        products.add(new Product(3, "Milk", 30));
        products.add(new Product(4, "Oil", 120));
        products.add(new Product(5, "Salt", 20));
        products.add(new Product(6, "Soap", 35));
        products.add(new Product(7, "Shampoo", 90));
        products.add(new Product(8, "Biscuits", 25));
        products.add(new Product(9, "Tea", 150));
        products.add(new Product(10, "Coffee", 200));
    }
    static void showProducts() {
        System.out.println("\n===== PRODUCT LIST =====");
        for (Product p : products) {
            System.out.println(p.id + " - " + p.name + " : " + p.price);
        }
    }
}