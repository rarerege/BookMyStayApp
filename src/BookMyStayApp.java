import java.util.*;

// Reservation request
class Reservation {
    private String guestName;
    private String roomType;


    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

}

// Centralized inventory
class RoomInventory {
    private Map<String, Integer> inventory;


    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2); // initial availability
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void allocateRoom(String roomType) {
        int current = inventory.getOrDefault(roomType, 0);
        if (current > 0) {
            inventory.put(roomType, current - 1);
        }
    }

}

// Booking queue
class BookingRequestQueue {
    private Queue<Reservation> queue;


    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) { queue.offer(r); }
    public Reservation pollRequest() { return queue.poll(); }
    public boolean isEmpty() { return queue.isEmpty(); }

}

// Booking service
class BookingService {
    private RoomInventory inventory;
    private Map<String, Integer> roomCounters; // per room type counter


    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        roomCounters = new HashMap<>();
    }

    public void processReservation(Reservation r) {
        String type = r.getRoomType();

        if (inventory.isAvailable(type)) {
            // Generate room ID: <RoomType>-<number>
            int count = roomCounters.getOrDefault(type, 0) + 1;
            roomCounters.put(type, count);
            String roomId = type + "-" + count;

            // Allocate room (decrement inventory)
            inventory.allocateRoom(type);

            // Confirm reservation
            System.out.println("Booking confirmed for Guest: " + r.getGuestName()
                    + ", Room ID: " + roomId);
        }
    }

    public void processQueue(BookingRequestQueue queue) {
        while (!queue.isEmpty()) {
            Reservation r = queue.pollRequest();
            processReservation(r);
        }
    }


}

// Main class
public class BookMyStayApp {


    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Add requests
        requestQueue.addRequest(new Reservation("Abhi", "Single"));
        requestQueue.addRequest(new Reservation("Subha", "Single"));
        requestQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process bookings
        BookingService service = new BookingService(inventory);
        service.processQueue(requestQueue);
    }


}
