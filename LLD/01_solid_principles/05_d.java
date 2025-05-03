
interface NotificationSender {

    void send(String message);
}

class EmailSender implements NotificationSender {}

class SMSSender implements NotificationSender {
}

// It can work with any notification sender, not just email or SMS.
class NotificationService {

    NotificationSender sender;
}

// This violated the dependency inversion principle, as the high-level module (NotificationService) depends on a low-level module (EmailSender).
// Due to which it is tightly coupled with the EmailSender class. If we want to change the sender to SMS, we have to change the code in NotificationService class as well.
class NotificationService {
    EmailSender sender = new EmailSender();
    void notify(String msg) {
        sender.send(msg);
    }
}
