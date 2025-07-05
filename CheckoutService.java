
import java.util.List;

public class CheckoutService {
    private CheckoutService() {}

    public static double checkout(Cart cart, Customer customer){
        if (cart == null || customer == null) {
            throw new IllegalArgumentException("Cart and customer cannot be null");
        }

        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        double subTotal = cart.getTotalPrice();
        System.out.println("****************** Checkout Summary ******************");
        cart.PrintCartItems();
        
        System.out.println("****************** Shipment Notice  ******************");
        List<CartItem> items = cart.getItems();
        ShippingService.PrintShippingCosts(items);
        double shippingCost = ShippingService.calculateShippingCost(items);
        double totalAmount = subTotal + shippingCost;

        if(totalAmount > customer.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance for checkout");
        }
        customer.setBalance(customer.getBalance() - totalAmount);

        System.out.println("------------------------------------------------------");
        System.out.printf("Subtotal: %.2f\n", subTotal);
        System.out.printf("Shipping Cost: %.2f\n", shippingCost);
        System.out.printf("Total Amount: %.2f\n", totalAmount);
        System.out.printf("Remaining Balance: %.2f\n", customer.getBalance());

        return totalAmount;
    }
}
