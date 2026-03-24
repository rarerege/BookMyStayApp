/**
 * UseCase2RoomInitialization
 *
 * Demonstrates basic room types using inheritance, static availability, and room sizes.
 *
 * @version 2.1
 */

abstract class Room {
    protected String roomType;
    protected int beds;
    protected int size; // in sqft
    protected double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public abstract void displayDetails();
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single", 1, 250, 1500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Single Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + BookMyStayApp.singleRoomAvailability + "\n");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double", 2, 400, 2500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Double Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + BookMyStayApp.doubleRoomAvailability + "\n");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite", 3, 750, 5000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + BookMyStayApp.suiteRoomAvailability + "\n");
    }
}

public class BookMyStayApp {

    // Make static availability variables public
    public static int singleRoomAvailability = 5;
    public static int doubleRoomAvailability = 3;
    public static int suiteRoomAvailability = 2;

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details
        single.displayDetails();
        doubleRoom.displayDetails();
        suite.displayDetails();
    }
}