import java.util.Queue;
import java.util.LinkedList;
class Reservation {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}
class BookingQueue {
    private Queue<Reservation> queue;
    public BookingQueue() {
        queue = new LinkedList<>();
    }
    public void addRequest(Reservation r) {
        queue.add(r);
    }
    public void displayQueue() {
        System.out.println("===== Booking Requests (FIFO) =====");
        for (Reservation r : queue) {
            r.display();
        }
    }
}
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        BookingQueue bookingQueue = new BookingQueue();
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.displayQueue();
    }
}