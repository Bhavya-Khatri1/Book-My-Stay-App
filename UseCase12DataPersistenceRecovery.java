import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String toString() {
        return guestName + " | " + roomType;
    }
}
class RoomInventory implements Serializable {
    Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }
    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}
class PersistenceService {
    private static final String FILE_NAME = "data.ser";
    public static void save(RoomInventory inventory, List<Reservation> bookings) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            oos.writeObject(bookings);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
    public static Object[] load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            RoomInventory inventory = (RoomInventory) ois.readObject();
            List<Reservation> bookings = (List<Reservation>) ois.readObject();
            System.out.println("Data loaded successfully.");
            return new Object[]{inventory, bookings};
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}
public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        RoomInventory inventory;
        List<Reservation> bookings;
        Object[] data = PersistenceService.load();
        if (data != null) {
            inventory = (RoomInventory) data[0];
            bookings = (List<Reservation>) data[1];
        } else {
            inventory = new RoomInventory();
            bookings = new ArrayList<>();
        }
        bookings.add(new Reservation("Alice", "Single Room"));
        inventory.display();
        System.out.println("Bookings: " + bookings);
        PersistenceService.save(inventory, bookings);
    }
}