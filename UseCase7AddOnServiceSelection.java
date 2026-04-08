import java.util.*;

class AddOnService {
    String name;
    double price;
    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap;
    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);
        if (services != null) {
            for (AddOnService s : services) {
                total += s.price;
            }
        }
        return total;
    }
    public void displayServices(String reservationId) {
        System.out.println("Services for Reservation: " + reservationId);
        List<AddOnService> services = serviceMap.get(reservationId);
        if (services != null) {
            for (AddOnService s : services) {
                System.out.println(s.name + " | ₹" + s.price);
            }
        } else {
            System.out.println("No services added.");
        }
    }
}
public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        String reservationId = "RSV_101";
        AddOnServiceManager manager = new AddOnServiceManager();
        manager.addService(reservationId, new AddOnService("Breakfast", 300));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 800));
        manager.addService(reservationId, new AddOnService("Spa", 1200));
        manager.displayServices(reservationId);
        double total = manager.calculateTotalCost(reservationId);
        System.out.println("Total Add-On Cost: ₹" + total);
    }
}