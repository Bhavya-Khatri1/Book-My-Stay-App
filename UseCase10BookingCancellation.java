import java.util.*;

class Reservation {
    String reservationId;
    String roomType;
    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}
class RoomInventory {
    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }
    public void increase(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }
    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}
class CancellationService {
    private Map<String, Reservation> confirmedBookings;
    private Stack<String> rollbackStack;
    private RoomInventory inventory;
    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
        confirmedBookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }
    public void addBooking(Reservation r) {
        confirmedBookings.put(r.reservationId, r);
    }
    public void cancel(String reservationId) {
        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Invalid Reservation ID");
            return;
        }
        Reservation r = confirmedBookings.get(reservationId);
        rollbackStack.push(reservationId);
        inventory.increase(r.roomType);
        confirmedBookings.remove(reservationId);
        System.out.println("Cancelled: " + reservationId);
    }
    public void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}
public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService(inventory);
        service.addBooking(new Reservation("R001", "Single Room"));
        service.addBooking(new Reservation("R002", "Double Room"));
        service.cancel("R001");
        service.cancel("R999");
        inventory.display();
        service.showRollbackStack();
    }
}