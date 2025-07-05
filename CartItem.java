
import java.util.UUID;

public class CartItem {
    private final UUID productId;
    private int quantity;

    public CartItem(UUID id, int quantity) {
        this.productId = id;
        this.setQuantity(quantity);
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity can only be a positive integer");
        }

        this.quantity = quantity;
    }

    public double getPrice() {
        return Repository.getProductPrice(productId) * quantity;
    }

    public CartItem copy() {
        return new CartItem(this.productId, this.quantity);
    }
}
