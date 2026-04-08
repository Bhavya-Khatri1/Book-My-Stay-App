import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}
class RoomInventory {
    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
    }
    public synchronized boolean allocate(String type) {
        int available = inventory.getOrDefault(type, 0);
        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }
}
class BookingProcessor extends Thread {
    private Queue<Reservation> queue;
    private RoomInventory inventory;
    public BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }
    public void run() {
        while (true) {
            Reservation r;
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.poll();
            }
            boolean success = inventory.allocate(r.roomType);
            if (success) {
                System.out.println(Thread.currentThread().getName() +
                        " → Booked for " + r.guestName);
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " → Failed for " + r.guestName);
            }
        }
    }
}
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room"));
        RoomInventory inventory = new RoomInventory();
        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);
        t1.start();
        t2.start();
    }
}