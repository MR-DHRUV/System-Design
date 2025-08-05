// Java StringBuilder is based on the Builder Design Pattern

// Product
class Computer {
    String cpu;
    String ram;
    String storage;
    String gpu;
    boolean hasGraphicsCard = false;

    public String toString() {
        return "CPU: " + cpu + ", RAM: " + ram + ", Storage: " + storage
                + ", GPU: " + (hasGraphicsCard ? gpu : "Integrated Graphics");
    }
}

// Builder Interface
interface ComputerBuilder {

    ComputerBuilder setCPU(String cpu);

    ComputerBuilder setRAM(String ram);

    ComputerBuilder setStorage(String storage);

    ComputerBuilder setGraphicsCard(boolean hasGPU);

    Computer build();
}

// Concrete Builder
class GamingComputerBuilder implements ComputerBuilder {

    private Computer computer = new Computer();

    public ComputerBuilder setCPU(String cpu) {
        computer.cpu = cpu;
        return this; // this allows method chaining
    }

    public ComputerBuilder setRAM(String ram) {
        computer.ram = ram;
        return this;
    }

    public ComputerBuilder setStorage(String storage) {
        computer.storage = storage;
        return this;
    }

    public ComputerBuilder setGraphicsCard(String gpu) {
        computer.hasGraphicsCard = true;
        computer.gpu = gpu;
        return this;
    }

    public Computer build() {
        return computer;
    }
}

class OfficeComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public OfficeComputerBuilder() {
        this.computer = new Computer();
    }

    public ComputerBuilder setCPU(String cpu) {
        computer.cpu = cpu;
        return this;
    }

    public ComputerBuilder setRAM(String ram) {
        computer.ram = ram;
        return this;
    }

    public ComputerBuilder setStorage(String storage) {
        computer.storage = storage;
        return this;
    }

    public ComputerBuilder setGraphicsCard(String gpu) {
        computer.hasGraphicsCard = true;
        computer.gpu = gpu;
        return this;
    }

    public Computer build() {
        return computer;
    }
}

// Director
class ComputerDirector {

    public Computer constructGamingComputer(ComputerBuilder builder) {
        return builder
                .setCPU("Intel Core i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard(true)
                .build();
    }

    public Computer constructOfficeComputer(ComputerBuilder builder) {
        return builder
                .setCPU("Intel Core i5")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .setGraphicsCard(false)
                .build();
    }
}
