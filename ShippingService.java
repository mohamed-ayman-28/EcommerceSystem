
import java.util.List;

public class ShippingService {

    private static double pricePerKg = 5;

    private ShippingService() {}

    public static double calculateShippingCost(List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be null or empty");
        }
        double totalWeight = 0;
        for (CartItem item : items) {
            if (Repository.getProduct(item.getProductId()) instanceof Shippable) {
                totalWeight += ((Shippable) Repository.getProduct(item.getProductId())).getWeight() * item.getQuantity();
            }
        }
        return totalWeight * pricePerKg;
    }

    public static void PrintShippingCosts(List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be null or empty");
        }
        double totalWeight = 0;
        for (CartItem item : items) {
            if (Repository.getProduct(item.getProductId()) instanceof Shippable) {
                Shippable product = (Shippable) Repository.getProduct(item.getProductId());
                double weight = product.getWeight() * item.getQuantity();
                System.out.printf("x%-10d %-30s %-10.2fg\n", item.getQuantity(), product.getName(), weight);
            }
        }
    }



    public static double getPricePerKg() {
        return pricePerKg;
    }

    public static void setPricePerKg(double pricePerKg) {
        if (pricePerKg <= 0) {
            throw new IllegalArgumentException("Price per kg must be a positive number");
        }
        ShippingService.pricePerKg = pricePerKg;
    }
}
