/**

 * UseCase10BookingCancellation
 *
 * Handles booking cancellations with rollback and inventory update.
 * Matches the expected concise output format.
 *
 * @version 10.1
 */

import java.util.*;

class Reservation {
    private String guestName;
    private String roomId;
    private String roomType;


    public Reservation(String guestName, String roomId, String roomType) {
        this.guestName = guestName;
        this.roomId = roomId;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomId() { return roomId; }
    public String getRoomType() { return roomType; }

}

// Inventory manager
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();


    public RoomInventory() {
        inventory.put("Single", 5); // Example: initial count
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void restoreRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

}

// Cancellation service
class CancellationService {
    private Stack<String> rollbackStack = new Stack<>();
    private RoomInventory inventory;
    private Map<String, Reservation> activeReservations;


    public CancellationService(RoomInventory inventory, Map<String, Reservation> activeReservations) {
        this.inventory = inventory;
        this.activeReservations = activeReservations;
    }

    public void cancelBooking(String roomId) {
        if (!activeReservations.containsKey(roomId)) {
            System.out.println("Cancellation failed: Reservation not found for ID " + roomId);
            return;
        }

        Reservation res = activeReservations.remove(roomId);

        // Rollback inventory and record released room
        inventory.restoreRoom(res.getRoomType());
        rollbackStack.push(res.getRoomId());

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + res.getRoomType());
        System.out.println("Rollback History (Most Recent First):");
        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }

        System.out.println("Updated " + res.getRoomType() + " Room Availability: " + inventory.getAvailability(res.getRoomType()));
    }


}

// Main class
public class BookMyStayApp {


    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Active reservations
        Map<String, Reservation> activeReservations = new HashMap<>();
        activeReservations.put("Single-1", new Reservation("Abhi", "Single-1", "Single"));

        CancellationService cancellationService = new CancellationService(inventory, activeReservations);

        System.out.println("Booking Cancellation\n");

        // Cancel a booking
        cancellationService.cancelBooking("Single-1");
    }


}
