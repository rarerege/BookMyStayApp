import java.util.*;

// Reservation with Room ID and base cost
class Reservation {
    private String guestName;
    private String roomId;
    private double roomCost;


    public Reservation(String guestName, String roomId, double roomCost) {
        this.guestName = guestName;
        this.roomId = roomId;
        this.roomCost = roomCost;
    }

    public String getRoomId() { return roomId; }
    public double getRoomCost() { return roomCost; }


}

// Add-On Service
class Service {
    private double cost;


    public Service(double cost) { this.cost = cost; }
    public double getCost() { return cost; }


}

// Add-On Service Manager
class AddOnServiceManager {
    private Map<String, List<Service>> reservationServices = new HashMap<>();


    public void addService(Reservation reservation, Service service) {
        reservationServices.putIfAbsent(reservation.getRoomId(), new ArrayList<>());
        reservationServices.get(reservation.getRoomId()).add(service);
    }

    public double getTotalAddOnCost(Reservation reservation) {
        double total = 0;
        List<Service> services = reservationServices.get(reservation.getRoomId());
        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }
        // Total includes room cost
        return total + reservation.getRoomCost();
    }


}

// Main class
public class BookMyStayApp {


    public static void main(String[] args) {

        // Reservation with base room cost
        Reservation res1 = new Reservation("Abhi", "Single-1", 500.0);

        // Services
        Service breakfast = new Service(500.0);
        Service spa = new Service(500.0);

        // Initialize service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(res1, breakfast);
        manager.addService(res1, spa);

        // Display total cost
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + res1.getRoomId());
        System.out.println("Total Add-On Cost: " + manager.getTotalAddOnCost(res1));
    }


}
