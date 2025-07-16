
// Prototype Interface
// Cloneable is a default interface in Java that any class can implement to allow cloning of its instances.
interface Shape extends Cloneable {
    Shape clone();

    void draw();
}

// Concrete Prototype classes
class Circle implements Shape {

    private int radius;
    private String color;

    public Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius);
    }

    public Shape clone() {
        return new Circle(this.radius, this.color);
    }
}

class Rectangle implements Shape {

    private int width, height;
    private String color;

    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw() {
        System.out.println("Drawing a " + color + " rectangle of size " + width + "x" + height);
    }

    public Shape clone() {
        return new Rectangle(this.width, this.height, this.color);
    }
}

// client code
public class Main {
    public static void main(String[] args) {
        Shape originalCircle = new Circle(10, "Red");
        Shape clonedCircle = originalCircle.clone();

        Shape originalRectangle = new Rectangle(20, 10, "Blue");
        Shape clonedRectangle = originalRectangle.clone();

        originalCircle.draw();     // Drawing a Red circle...
        clonedCircle.draw();       // Drawing a Red circle...

        originalRectangle.draw();  // Drawing a Blue rectangle...
        clonedRectangle.draw();    // Drawing a Blue rectangle...
    }
}
