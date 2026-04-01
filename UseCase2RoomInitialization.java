abstract class Room {
    protected String type;
    protected int beds;
    protected double price;
    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
    }
}
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500);
    }
}
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500);
    }
}
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;
        System.out.println("===== Room Details =====");
        single.displayDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println();
        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println();
        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}