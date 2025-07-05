
public class Main {
    public static void main(String[] args) {
        try {
            // Create products
            Product p1 = null, p2 = null, p3 = null, p4 = null;
            try { p1 = new Product("Laptop", 1000.0); } catch (Exception e) { System.out.println("Create p1: " + e.getMessage()); }
            try { p2 = new Product("Phone", 500.0); } catch (Exception e) { System.out.println("Create p2: " + e.getMessage()); }
            try { p3 = new Product("Book", 20.0); } catch (Exception e) { System.out.println("Create p3: " + e.getMessage()); }
            try { p4 = new Product("Headphones", 100.0); } catch (Exception e) { System.out.println("Create p4: " + e.getMessage()); }

            // Add products to repository
            try { if (p1 != null) Repository.addProduct(p1); } catch (Exception e) { System.out.println("Add product p1: " + e.getMessage()); }
            try { if (p2 != null) Repository.addProduct(p2); } catch (Exception e) { System.out.println("Add product p2: " + e.getMessage()); }
            try { if (p4 != null) Repository.addProduct(p4); } catch (Exception e) { System.out.println("Add product p4: " + e.getMessage()); }
            // Try to add duplicate product
            try { if (p1 != null) Repository.addProduct(p1); } catch (Exception e) { System.out.println("Duplicate product test: " + e.getMessage()); }
            // Try to add null product
            try { Repository.addProduct(null); } catch (Exception e) { System.out.println("Null product test: " + e.getMessage()); }

            // Create customer with insufficient balance
            Customer customer1 = null;
            try {
                customer1 = new Customer("Alice");
                customer1.setBalance(200.0);
            } catch (Exception e) { System.out.println("Create customer1: " + e.getMessage()); }

            // Create customer with enough balance
            Customer customer2 = null;
            try {
                customer2 = new Customer("Bob");
                customer2.setBalance(2000.0);
            } catch (Exception e) { System.out.println("Create customer2: " + e.getMessage()); }

            // Create cart and add items
            Cart cart1 = null;
            try {
                cart1 = new Cart();
                if (p1 != null) cart1.addItem(p1.getId(), 1); // Laptop
                if (p2 != null) cart1.addItem(p2.getId(), 1); // Phone
                if (p4 != null) cart1.addItem(p4.getId(), 2); // Headphones
            } catch (Exception e) { System.out.println("Create cart1 or add items: " + e.getMessage()); }

            // Try to add item with invalid quantity
            try { if (cart1 != null && p2 != null) cart1.addItem(p2.getId(), 0); } catch (Exception e) { System.out.println("Invalid quantity test: " + e.getMessage()); }
            // Try to add item for out-of-stock product
            try { if (cart1 != null && p3 != null) cart1.addItem(p3.getId(), 1); } catch (Exception e) { System.out.println("Out of stock test: " + e.getMessage()); }

            // Try checkout with insufficient balance
            try { if (cart1 != null && customer1 != null) CheckoutService.checkout(cart1, customer1); } catch (Exception e) { System.out.println("Insufficient balance test: " + e.getMessage()); }
            // Try checkout with enough balance
            try { if (cart1 != null && customer2 != null) { double total = CheckoutService.checkout(cart1, customer2); System.out.println("Checkout successful! Total paid: " + total); } } catch (Exception e) { System.out.println("Checkout failed: " + e.getMessage()); }

            // Try checkout with empty cart
            Cart emptyCart = null;
            try { emptyCart = new Cart(); } catch (Exception e) { System.out.println("Create empty cart: " + e.getMessage()); }
            try { if (emptyCart != null && customer2 != null) CheckoutService.checkout(emptyCart, customer2); } catch (Exception e) { System.out.println("Empty cart test: " + e.getMessage()); }
            // Try checkout with null cart
            try { if (customer2 != null) CheckoutService.checkout(null, customer2); } catch (Exception e) { System.out.println("Null cart test: " + e.getMessage()); }
            // Try checkout with null customer
            try { if (cart1 != null) CheckoutService.checkout(cart1, null); } catch (Exception e) { System.out.println("Null customer test: " + e.getMessage()); }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
