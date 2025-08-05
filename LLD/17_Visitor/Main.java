// element interface
import java.util.List;

interface Shape {
    void accept(ShapeVisitor visitor);
}

// concrete element
class Circle implements Shape {
    int radius = 5;

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }
}

class Rectangle implements Shape {
    int length = 10;
    int width = 5;

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }
}

// visitor interface
interface ShapeVisitor {
    void visitCircle(Circle circle);

    void visitRectangle(Rectangle rectangle);
}

// concrete visitor
class JsonExportVisitor implements ShapeVisitor {
    @Override
    public void visitCircle(Circle circle) {
        System.out.println("Exporting Circle to JSON with radius: " + circle.radius);
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        System.out.println("Exporting Rectangle to JSON with length: " + rectangle.length + " and width: " + rectangle.width);
    }
}


public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Circle(), new Rectangle());
        ShapeVisitor jsonExporter = new JsonExportVisitor();

        for (Shape s : shapes) {
            s.accept(jsonExporter); // Visitor operates on shape
        }
    }
}