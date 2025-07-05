
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Repository {
    private static final Map<UUID, Product> products = new HashMap<>();

    private Repository() {
        // Private constructor to prevent instantiation
    }

    public static void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Invalid product");
        }else if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with this ID already exists");
        }
        products.put(product.getId(), product);
    }

    public static void removeProduct(UUID productId) {
        if (productId == null || !products.containsKey(productId)) {
            throw new IllegalArgumentException("Product not found");
        }
        products.remove(productId);
    }

    public static void addToStock(UUID productId, int quantity) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }
        product.setAvailableQuantity(product.getAvailableQuantity() + quantity);
    }

    public static void removeFromStock(UUID productId, int quantity) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }
        int availableQuantity = product.getAvailableQuantity();
        if (availableQuantity < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        product.setAvailableQuantity(availableQuantity - quantity);
    }

    public static Product getProduct(UUID productId) {
        return products.get(productId);
    }

    public static double getProductPrice(UUID productId) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        return product.getPrice();
    }

    public static int getAvailableQuantity(UUID productId) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        return product.getAvailableQuantity();
    }

    public static void setAvailableQuantity(UUID productId, int quantity) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        product.setAvailableQuantity(quantity);
    }

    public static void setProductPrice(UUID productId, double price) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        product.setPrice(price);
    }

    public static void setProductName(UUID productId, String name) {
        Product product = getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        product.setName(name);
    }
}
