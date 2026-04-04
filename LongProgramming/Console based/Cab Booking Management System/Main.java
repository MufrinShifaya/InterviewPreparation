import java.util.*;

public class Main {
    public static void main(String[] args) {

        HashSet<User> userinfo = new HashSet<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Book Cab");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {

                User u = new User();

                System.out.println("Enter name:");
                u.setname(sc.nextLine());

                System.out.println("Enter ID:");
                u.setuserId(sc.nextInt());
                sc.nextLine();

                System.out.println("Enter phone:");
                u.setphonenumber(sc.nextLong());
                sc.nextLine();

                System.out.println("Enter pickup:");
                u.setpickuploc(sc.nextLine());

                System.out.println("Enter destination:");
                u.setdestinationloc(sc.nextLine());

                System.out.println("Enter distance (km):");
                u.setdistance(sc.nextInt());
                sc.nextLine();

                userinfo.add(u);

                System.out.println("User added successfully!");
            }
            else if (choice == 2) {

                if (userinfo.isEmpty()) {
                    System.out.println("No users available. Add user first!");
                    continue;
                }

                ArrayList<Driver> drivers = new ArrayList<>();
                drivers.add(new Driver("Abi", 1, 1111111111L));
                drivers.add(new Driver("Pradeep", 2, 2222222222L));
                drivers.add(new Driver("Mani", 3, 3333333333L));
                drivers.add(new Driver("Vignesh", 4, 4444444444L));
                drivers.add(new Driver("Zohan", 5, 5555555555L));

                // 🔹 Select first user (for now)
                User selectedUser = null;
                for (User user : userinfo) {
                    selectedUser = user;
                    break;
                }
                int bookingId = (int)(Math.random() * 1000);
                Driver assignedDriver = drivers.get(0);
                assignedDriver.setdriverId(bookingId);
                String vehicleId = "TN" + bookingId;
                Booking booking = new Booking(bookingId, vehicleId, selectedUser, assignedDriver);
                int costPerKm = 10;
                int distance = selectedUser.getdistance();
                int amount = distance * costPerKm;
                System.out.println("Enter payment method (Cash/UPI/Card):");
                String method = sc.nextLine();
                Payment payment = new Payment(bookingId, method, amount, 101);

                System.out.println("\n--- Booking Confirmed ---");
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Vehicle ID: " + booking.getVehicleId());
                System.out.println("User: " + booking.getUser().getname());
                System.out.println("Driver: " + booking.getDriver().getdrivername());
                System.out.println("Driver ID: " + booking.getDriver().getdriverId());
                System.out.println("Driver Phone: " + booking.getDriver().getphonenumber());
                System.out.println("From: " + booking.getUser().getpickuploc());
                System.out.println("To: " + booking.getUser().getdestinationloc());
                System.out.println("Distance: " + booking.getUser().getdistance() + " km");
                System.out.println("Amount: " + payment.getamount());
                System.out.println("Payment: " + payment.getmethod());
                System.out.println("--------------------------");
            }
            else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
        }
    }
}