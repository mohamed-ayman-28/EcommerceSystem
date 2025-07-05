
import java.util.UUID;

public class Customer {
    // no need for setters here , as the client code should never be able to change these values
    // once the customer is created, these values should remain constant
    // this is a good practice to ensure immutability for the Customer class
    // and to prevent accidental changes to the customer's data
    private final String name;
    private final UUID id;
    private double balance;

    public Customer(String name) {
        this.name = name;
        this.id = UUID.randomUUID(); // Generate a unique ID for the customer. Since there
                                    // is no real database, we generate it here
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance <= 0) {
            throw new IllegalArgumentException("Balance can't be negative");
        }
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
