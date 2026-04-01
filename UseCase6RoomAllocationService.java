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
    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
    public void decrease(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}
class BookingService {
    private Queue<Reservation> queue;
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
    }
    public void processBookings() {
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            String type = r.roomType;
            if (inventory.getAvailability(type) > 0) {
                String roomId = type.substring(0, 2).toUpperCase() + "_" + UUID.randomUUID().toString().substring(0, 4);
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                Set<String> set = allocatedRooms.get(type);
                set.add(roomId);
                inventory.decrease(type);
                System.out.println("Booking Confirmed → " + r.guestName + " | " + type + " | Room ID: " + roomId);
            } else {
                System.out.println("Booking Failed → " + r.guestName + " | " + type + " (No Availability)");
            }
        }
    }
}
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Single Room")); // should fail
        queue.add(new Reservation("David", "Suite Room"));
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(queue, inventory);
        service.processBookings();
    }
}