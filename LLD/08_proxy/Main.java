// Subject
interface Image {
    void display();
}

// Real subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        if (checkAccess()) {
            System.out.println("Access granted to " + filename);
        } else {
            System.out.println("Access denied to " + filename);
            return;
        }
        realImage.display();
    }

    private boolean checkAccess() {
        // Simulate access control
        System.out.println("Checking access for " + filename);
        return true; // Allow access
    }
}


public class Main {
    public static void main(String[] args) {
        Image img = new ProxyImage("test_image.jpg");

        // Image will be loaded from disk
        img.display();

        // Image will not be loaded from disk
        img.display();
    }
}
