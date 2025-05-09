// Product interface
interface Transport {
    void deliver();
}

// Concrete Product classes
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by truck.");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by ship.");
    }
}

// Creator class
abstract class Logistics {
    // The factory method—clients call this to get a Transport
    protected abstract Transport createTransport();

    // Business logic remains in the base class
    public void planDelivery() {
        Transport t = createTransport();
        t.deliver();
    }
}

// Concrete Creator classes
class RoadLogistics extends Logistics {
    @Override
    protected Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    protected Transport createTransport() {
        return new Ship();
    }
}

public class Main {
    // Client code
    public static void main(String[] args) {
        Logistics logistics = new RoadLogistics();
        logistics.planDelivery(); // Output: Delivering by truck.
    }
}
