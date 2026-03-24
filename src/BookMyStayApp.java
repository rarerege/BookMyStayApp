import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {
    protected int beds;
    protected int size;
    protected double price;


    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public abstract void displayDetails(int availability);


}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }


    public void displayDetails(int availability) {
        System.out.println("Single Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availability);
        System.out.println();
    }


}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }


    public void displayDetails(int availability) {
        System.out.println("Double Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availability);
        System.out.println();
    }


}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }


    public void displayDetails(int availability) {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + availability);
    }


}

// Centralized Inventory
class RoomInventory {
    private Map<String, Integer> inventory;


    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }


}

// Main Class
public class BookMyStayApp {


    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display rooms with centralized availability
        single.displayDetails(inventory.getAvailability("Single Room"));
        doubleRoom.displayDetails(inventory.getAvailability("Double Room"));
        suite.displayDetails(inventory.getAvailability("Suite Room"));
    }


}
