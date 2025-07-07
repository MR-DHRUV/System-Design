// Target Interface: our system uses this
interface PaymentProcessor {
    void pay(double amount);
}

// Some existing classes
class StripePayment implements PaymentProcessor {

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Stripe.");
    }
}

// Adaptee: this is the new payment provider we want to integrate
class NewPaymentProvider {

    public void makeTransaction(double amountInDollars) {
        System.out.println("Transaction of $" + amountInDollars + " processed using NewPaymentProvider.");
    }
}

// Adapter: this adapts the new payment provider to the existing interface
class NewPaymentProviderAdapter implements PaymentProcessor {

    private NewPaymentProvider newProvider;

    public NewPaymentProviderAdapter(NewPaymentProvider provider) {
        this.newProvider = provider;
    }

    @Override
    public void pay(double amount) {
        newProvider.makeTransaction(amount); // Adapts the interface
    }
}


public class Main {
    public static void main(String[] args) {
        PaymentProcessor stripe = new StripePayment();
        stripe.pay(100.0);

        PaymentProcessor newProvider = new NewPaymentProviderAdapter(new NewPaymentProvider());
        newProvider.pay(200.0);
    }
}
