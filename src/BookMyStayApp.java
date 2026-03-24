import java.util.Scanner;

public class BookMyStayApp {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Booking Validation");

        System.out.print("Enter guest name: ");
        String guestName = sc.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = sc.nextLine();

        // Validate room type (case-sensitive)
        if (!roomType.equals("Single") && !roomType.equals("Double") && !roomType.equals("Suite")) {
            System.out.println("Booking failed: Invalid room type selected.");
        } else {
            System.out.println("Booking successful for Guest: " + guestName
                    + ", Room Type: " + roomType);
        }

        sc.close();
    }


}
