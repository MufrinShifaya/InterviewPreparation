import java.util.Scanner;

class Vehicle {
    private String number;
    Vehicle(String number) { this.number = number; }
    String getNumber() { return number; }
}

class Slot {
    private int id;
    private Vehicle vehicle;
    Slot(int id) { this.id = id; }
    boolean isEmpty() { return vehicle == null; }
    void park(Vehicle v) { vehicle = v; }
    void remove() { vehicle = null; }
    Vehicle getVehicle() { return vehicle; }
    int getId() { return id; }
}

class ParkingLot {
    private Slot[] slots;
    private int availableSlots;

    ParkingLot() { // default 5 slots
        int size = 5;
        slots = new Slot[size];
        availableSlots = size;
        for (int i = 0; i < size; i++) slots[i] = new Slot(i + 1);
        System.out.println(" Parking Lot created with " + size + " slots.");
    }

    void parkVehicle(Vehicle v) {
        if (availableSlots == 0) {
            System.out.println("Parking Full! No slots available.");
            return;
        }
        for (Slot s : slots) {
            if (s.isEmpty()) {
                s.park(v);
                availableSlots--;
                System.out.println(" Vehicle " + v.getNumber() + " parked at slot " + s.getId() +
                                   " | Available slots: " + availableSlots);
                return;
            }
        }
    }

    void removeVehicleByNumber(String vehicleNumber) {
        for (Slot s : slots) {
            if (!s.isEmpty() && s.getVehicle().getNumber().equals(vehicleNumber)) {
                s.remove();
                availableSlots++;
                System.out.println(" Vehicle " + vehicleNumber + " removed from slot " + s.getId() +
                                   " | Available slots: " + availableSlots);
                return;
            }
        }
        System.out.println("Vehicle " + vehicleNumber + " not found in parking lot!");
    }

    void displayStatus() {
        System.out.println("\n Parking Lot Status | Available slots: " + availableSlots);
        for (Slot s : slots) {
            System.out.println("Slot " + s.getId() + " -> " + (s.isEmpty() ? "Empty" : s.getVehicle().getNumber()));
        }
        System.out.println();
    }
}

public class ParkingVehicle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot lot = new ParkingLot(); // default slots

        while (true) {
            System.out.println("1. Park Vehicle  2. Remove Vehicle by Number  3. Display  4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt(); sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Vehicle Number: ");
                String num = sc.nextLine();
                lot.parkVehicle(new Vehicle(num));
            } else if (choice == 2) {
                System.out.print("Enter Vehicle Number to remove: ");
                String num = sc.nextLine();
                lot.removeVehicleByNumber(num);
            } else if (choice == 3) {
                lot.displayStatus();
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid Option! Try again.");
            }
        }
        sc.close();
    }
}
