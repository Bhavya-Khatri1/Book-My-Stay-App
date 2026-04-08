import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String reservationId;
    public Reservation(String guestName, String roomType, String reservationId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.reservationId = reservationId;
    }
    public void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}
class BookingHistory {
    private List<Reservation> history;
    public BookingHistory() {
        history = new ArrayList<>();
    }
    public void addReservation(Reservation r) {
        history.add(r);
    }
    public List<Reservation> getAll() {
        return history;
    }
}
class BookingReportService {
    public void displayAll(List<Reservation> history) {
        System.out.println("===== Booking History =====");
        for (Reservation r : history) {
            r.display();
        }
    }
    public void summary(List<Reservation> history) {
        System.out.println("\n===== Summary =====");
        System.out.println("Total Bookings: " + history.size());
        Map<String, Integer> count = new HashMap<>();
        for (Reservation r : history) {
            count.put(r.roomType, count.getOrDefault(r.roomType, 0) + 1);
        }
        for (String type : count.keySet()) {
            System.out.println(type + " : " + count.get(type));
        }
    }
}
public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        history.addReservation(new Reservation("Alice", "Single Room", "R001"));
        history.addReservation(new Reservation("Bob", "Double Room", "R002"));
        history.addReservation(new Reservation("Charlie", "Single Room", "R003"));
        BookingReportService report = new BookingReportService();
        report.displayAll(history.getAll());
        report.summary(history.getAll());
    }
}