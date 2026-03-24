import java.util.*;

// Reservation class
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

// Booking History
class BookingHistory {
    private List<Reservation> history;


    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(history);
    }


}

// Reporting Service
class BookingReportService {
    private BookingHistory history;


    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void generateReport() {
        System.out.println("Booking History and Reporting");
        System.out.println("Booking History Report");

        for (Reservation r : history.getAllReservations()) {
            System.out.println("Guest: " + r.getGuestName() + ", Room Type: " + r.getRoomType());
        }
    }


}

// Main class
public class BookMyStayApp {


    public static void main(String[] args) {

        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService(bookingHistory);

        // Simulate confirmed bookings
        bookingHistory.addReservation(new Reservation("Abhi", "Single"));
        bookingHistory.addReservation(new Reservation("Subha", "Double"));
        bookingHistory.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        reportService.generateReport();
    }


}
