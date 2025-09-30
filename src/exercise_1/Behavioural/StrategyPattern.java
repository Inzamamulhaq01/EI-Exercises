package exercise_1.Behavioural;

// Strategy Pattern: Payment System
interface PaymentStrategy
{
    void pay(double amt);
}

// Concrete Strategies
class UpiPayment implements PaymentStrategy
{

    @Override
    public void pay(double amt) {
        System.out.println("Paid "+amt+" using UPI");
    }
}

class CreditCardPayment implements PaymentStrategy
{

    @Override
    public void pay(double amt) {
        System.out.println("Paid "+amt+" using credit card");
    }
}

// Context
class ShoppingCart {

    private PaymentStrategy paymentMethod;

    public void setStrategy(PaymentStrategy payment)
    {
        this.paymentMethod = payment;
    }

    public void checkout(int amt)
    {
        paymentMethod.pay(amt);
    }
}

// Client Code
public class StrategyPattern {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.setStrategy(new CreditCardPayment());
        cart.checkout(5500); // Output: Paid 5500.0 using credit card

        cart.setStrategy(new UpiPayment());
        cart.checkout(7400); // Output: Paid 7400.0 using UPI
    }

}


// created a payment system where different payment methods can be used interchangeably without altering the client code.
// The PaymentStrategy interface defines a common interface for all supported algorithms (payment methods).
// CreditCardPayment and UpiPayment are concrete implementations of the strategy interface.
// The ShoppingCart class is the Context that maintains a reference to the current strategy and allows switching between different strategies