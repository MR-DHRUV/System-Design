// component interface
interface Notifier {
    void send(String message);
};

// Concrete component class
class BasicNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending notification: " + message);
    }
}

// Base Decorator/ Decorator class
abstract class NotifierDecorator implements Notifier {

    protected Notifier wrappee;

    public NotifierDecorator(Notifier notifier) {
        this.wrappee = notifier;
    }

    @Override
    public void send(String message) {
        wrappee.send(message); // delegate to the wrapped notifier
    }
};

// Concrete Decorator classes
class EmailNotifier extends NotifierDecorator {

    public EmailNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // call the base class method
        System.out.println("Sending email: " + message);
    }
}

class SMSNotifier extends NotifierDecorator {

    public SMSNotifier(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // call the base class method
        System.out.println("Sending SMS: " + message);
    }
}

public class Main {
    public static void main(String[] args) {

        Notifier notifier = new BasicNotifier();
        notifier.send("Hello, World!");

        // Phase 1: Add email notification
        notifier = new EmailNotifier(notifier);
        notifier.send("Hello, World!");

        // Phase 2: Add SMS notification
        notifier = new SMSNotifier(notifier);
        notifier.send("Hello, World!");
        // Order: BasicNotifier -> EmailNotifier -> SMSNotifier
        // First delegate then execute their own logic

        // Phase 3: ...... Add another decorator if needed
    }
}
