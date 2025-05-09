// abtract product-1
abstract class Chair {
};

// Concrete product-1
class ModernChair extends Chair {
};
class VictorianChair extends Chair {
};

// Abstract product-2
abstract class Sofa {
};

// Concrete product-2
class ModernSofa extends Sofa {
};
class VictorianSofa extends Sofa {
};

// Abstract factory
abstract class FurnitureFactory {
    abstract Chair createChair();
    abstract Sofa createSofa();
};

// Concrete factory-1
class ModernFurnitureFactory extends FurnitureFactory {
    @Override
    Chair createChair() {
        return new ModernChair();
    }

    @Override
    Sofa createSofa() {
        return new ModernSofa();
    }
};

// Concrete factory-2
class VictorianFurnitureFactory extends FurnitureFactory {
    @Override
    Chair createChair() {
        return new VictorianChair();
    }

    @Override
    Sofa createSofa() {
        return new VictorianSofa();
    }
};

// Client code
public class Main {
    public static void main(String[] args) {
        FurnitureFactory fac = new ModernFurnitureFactory();
    }
}
