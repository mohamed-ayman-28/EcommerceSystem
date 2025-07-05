
import java.util.UUID;

public class Product {
    // Represents a product category in the system.
    // There is no need to represent individual instances of a product category
    // since it's assumed that there is no need to track individual products
    // of the same category in this system.
    private final UUID id;
    private String name;
    private double price;
    private int availableQuantity;

    public Product(String name, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailableQuantity(int availableQuantity) {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available product quantity cannot be negative");
        }
        this.availableQuantity = availableQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.price = price;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.name = name;
    }
}


