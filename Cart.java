
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class Cart {
    private HashMap<UUID, CartItem> items = new HashMap<>();

    public void addItem(UUID productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer");
        }

        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product product = Repository.getProduct(productId);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (items.containsKey(productId)) {
            if (product.getAvailableQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock available for this product");
            }
            CartItem existingItem = items.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }else{
            items.put(productId, new CartItem(productId, quantity));
        }
    }

    public void addItem(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product product = Repository.getProduct(productId);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (items.containsKey(productId)) {
            CartItem existingItem = items.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        }else{
            items.put(productId, new CartItem(productId, 1));
        }
    }

    public void removeItemFromCart(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        items.remove(productId);
    }

    public void decreaseItemQuantity(UUID productId, int decrease) {
        if(productId == null){
            throw  new IllegalArgumentException("Product ID cannot be null");
        }

        if(decrease <= 0){
            throw new IllegalArgumentException("Decrease value must be positive");
        }

        CartItem item = items.get(productId);
        if(item == null){
            throw new IllegalArgumentException("Product not found in cart");
        }

        if(decrease > item.getQuantity()){
            item.setQuantity(0);
        }else{
            item.setQuantity(item.getQuantity() - decrease);
        }
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (HashMap.Entry<UUID, CartItem> entry : items.entrySet()) {
            UUID key = entry.getKey();
            CartItem val = entry.getValue();
            Product product = Repository.getProduct(key);
            if (product != null) {
                totalPrice += product.getPrice() * val.getQuantity();
            }
        }
        return totalPrice;
    }

    public List<CartItem> getItems() {
        // this method returns a deep copy because no other party must be able to modify the list
        // an immutable view could have been return, but this won't prevent modifying the CartItem objects
        return items.values().stream().map(CartItem::copy)
        .collect(Collectors.toList()); 
    }

    public void PrintCartItems() {
        for(CartItem item : items.values()){
            System.out.printf("x%-10d %-30s %-10.2f \n", item.getQuantity(), Repository.getProduct(item.getProductId()).getName(), item.getPrice());
        }
    }

    public Boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public int getItemCount() {
        return items.size();
    }

    public int getTotalQuantity() {
        return items.values().stream().mapToInt(CartItem::getQuantity).sum();
    }
}
