import java.io.*;
import java.util.*;

class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    @Override
    public String toString() {
        return "Single: " + getAvailability("Single") +
                "\nDouble: " + getAvailability("Double") +
                "\nSuite: " + getAvailability("Suite");
    }
}

public class BookMyStayApp {

    private static final String FILE_NAME = "hotel_system_state.dat";

    public static void main(String[] args) {

        RoomInventory inventory;

        // Attempt to load persisted state
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("System Recovery");
            System.out.println("No valid inventory data found. Starting fresh.\n");
            inventory = new RoomInventory();
        } else {
            // In a full implementation, deserialization would occur here
            inventory = new RoomInventory();
        }

        // Display current inventory
        System.out.println("Current Inventory:");
        System.out.println(inventory);

        // Save the initial state
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}