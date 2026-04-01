import java.util.HashMap;
class RoomInventory {
    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}
abstract class Room {
    String type;
    double price;
    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }
    public void display() {
        System.out.println(type + " | Price: ₹" + price);
    }
}
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1500); }
}
class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2500); }
}
class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 5000); }
}
public class UseCase4RoomSearch {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        Room[] rooms = {
            new SingleRoom(),
            new DoubleRoom(),
            new SuiteRoom()
        };
        System.out.println("===== Available Rooms =====");
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.type);
            if (available > 0) {
                room.display();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}