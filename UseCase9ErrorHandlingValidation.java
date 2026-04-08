import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
class RoomInventory {
    private Map<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }
    public void decrease(String type) throws InvalidBookingException {
        int count = getAvailability(type);
        if (count <= 0) {
            throw new InvalidBookingException("No rooms available for: " + type);
        }
        inventory.put(type, count - 1);
    }
}
class BookingValidator {
    public static void validate(String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }
        if (inventory.getAvailability(roomType) == -1) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("No availability for: " + roomType);
        }
    }
}
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        String[] requests = {
            "Single Room",
            "Suite Room",
            "",
            "Double Room",
            "Double Room"
        };
        for (String roomType : requests) {
            try {
                BookingValidator.validate(roomType, inventory);
                inventory.decrease(roomType);
                System.out.println("Booking successful for: " + roomType);
            } catch (InvalidBookingException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}