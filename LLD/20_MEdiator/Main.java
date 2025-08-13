import java.util.*;

// mediator interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String msg, User sender) {
        for (User u : users) {
            if (u != sender) {
                u.receive(msg);
            }
        }
    }
}

// Colleague interface
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    abstract void send(String msg);
    abstract void receive(String msg);
}

// Concrete Colleague
class ChatUser extends User {

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    void send(String msg) {
        System.out.println(this.name + " sending: " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    void receive(String msg) {
        System.out.println(this.name + " received: " + msg);
    }
}

public class Main {    public static void main(String[] args) {
        ChatMediator chat = new ChatRoom();

        User u1 = new ChatUser(chat, "Alice");
        User u2 = new ChatUser(chat, "Bob");
        User u3 = new ChatUser(chat, "Charlie");

        chat.addUser(u1);
        chat.addUser(u2);
        chat.addUser(u3);

        u1.send("Hello Everyone!");
    }
}
