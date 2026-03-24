import java.util.LinkedList;
import java.util.Queue;

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;


    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

}

// Booking Queue
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    // Process requests in FIFO order
    public void processRequests() {
        System.out.println("Booking Request Queue");

        while (!queue.isEmpty()) {
            Reservation r = queue.poll(); // removes in FIFO order
            System.out.println("Processing booking for Guest: "
                    + r.getGuestName()
                    + ", Room Type: "
                    + r.getRoomType());
        }
    }


}

// Main class
public class BookMyStayApp {


    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add requests (arrival order)
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process queue
        bookingQueue.processRequests();
    }

}
