class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow flying");
    }
}

// This class violates the Liskov Substitution Principle (LSP) because it cannot fly, but it is a subclass of Bird which can fly.
// If we replace Bird with Ostrich in the code, it will throw an exception when we try to call the fly method.
class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich cannot fly");
    }
}

// Better approach
class FlyableBird {
    public void fly() {
        System.out.println("Flying");
    }
}

class UnflyableBird {
    public void walk() {
        System.out.println("Walking");
    }
}

// Now we can easily replace FlyableBird with any other class that can fly, and UnflyableBird with any other class that cannot