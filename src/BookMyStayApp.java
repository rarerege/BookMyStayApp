import java.util.*;
import java.util.concurrent.*;

// Reservation request
class ReservationRequest {
    private String guestName;
    private String roomType;


    public ReservationRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }


}

// Inventory with unique room IDs and thread-safe allocation
class RoomInventory {
    private Map<String, Queue<String>> availableRooms = new HashMap<>();


    public RoomInventory() {
        availableRooms.put("Single", new LinkedList<>(Arrays.asList("Single-1", "Single-2", "Single-3", "Single-4", "Single-5")));
        availableRooms.put("Double", new LinkedList<>(Arrays.asList("Double-1", "Double-2")));
        availableRooms.put("Suite", new LinkedList<>(Arrays.asList("Suite-1")));
    }

    public synchronized String allocateRoom(String roomType) {
        Queue<String> rooms = availableRooms.get(roomType);
        if (rooms != null && !rooms.isEmpty()) {
            return rooms.poll(); // Remove the first available room
        }
        return null; // No rooms available
    }

    public synchronized int getAvailability(String roomType) {
        Queue<String> rooms = availableRooms.get(roomType);
        return rooms == null ? 0 : rooms.size();
    }


}

// Booking task (Runnable)
class BookingTask implements Runnable {
    private ReservationRequest request;
    private RoomInventory inventory;


    public BookingTask(ReservationRequest request, RoomInventory inventory) {
        this.request = request;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        String roomId = inventory.allocateRoom(request.getRoomType());
        if (roomId != null) {
            System.out.println("Booking confirmed for Guest: " + request.getGuestName() + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for Guest: " + request.getGuestName() + " - Room Type unavailable: " + request.getRoomType());
        }
    }


}

// Main class
public class BookMyStayApp {


    public static void main(String[] args) throws InterruptedException {

        RoomInventory inventory = new RoomInventory();

        // Concurrent booking requests
        List<ReservationRequest> requests = Arrays.asList(
                new ReservationRequest("Abhi", "Single"),
                new ReservationRequest("Vanmathi", "Double"),
                new ReservationRequest("Kural", "Suite"),
                new ReservationRequest("Subha", "Single")
        );

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (ReservationRequest req : requests) {
            executor.submit(new BookingTask(req, inventory));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.getAvailability("Single"));
        System.out.println("Double: " + inventory.getAvailability("Double"));
        System.out.println("Suite: " + inventory.getAvailability("Suite"));
    }


}
