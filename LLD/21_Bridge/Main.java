// Implementor
class Renderer {
    void renderCircle(Circle circle) {
        System.out.println("Rendering circle with radius: " + circle.getRadius());
    }

    void renderRectangle(Rectangle rectangle) {
        System.out.println(
                "Rendering rectangle with width: " + rectangle.getWidth() + " and height: " + rectangle.getHeight());
    }
}

// Concrete Implementor
class DirectXRenderer extends Renderer {
    @Override
    void renderCircle(Circle circle) {
        System.out.println("DirectX rendering circle with radius: " + circle.getRadius());
    }

    @Override
    void renderRectangle(Rectangle rectangle) {
        System.out.println("DirectX rendering rectangle with width: " + rectangle.getWidth() + " and height: "
                + rectangle.getHeight());
    }
}

class OpenGLRenderer extends Renderer {
    @Override
    void renderCircle(Circle circle) {
        System.out.println("OpenGL rendering circle with radius: " + circle.getRadius());
    }

    @Override
    void renderRectangle(Rectangle rectangle) {
        System.out.println("OpenGL rendering rectangle with width: " + rectangle.getWidth() + " and height: "
                + rectangle.getHeight());
    }
}

// Abstraction
abstract class Shape {
    protected Renderer renderer; // Bridge link

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    abstract void draw();
}

// refined abstraction
class Circle extends Shape {
    private final int radius;

    public Circle(Renderer renderer, int radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    void draw() {
        renderer.renderCircle(this);
    }

    public int getRadius() {
        return radius;
    }
}

class Rectangle extends Shape {
    private final int width;
    private final int height;

    public Rectangle(Renderer renderer, int width, int height) {
        super(renderer);
        this.width = width;
        this.height = height;
    }

    @Override
    void draw() {
        renderer.renderRectangle(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


public class Main {
    public static void main(String[] args) {
        Renderer directXRenderer = new DirectXRenderer();
        Renderer openGLRenderer = new OpenGLRenderer();

        Shape circle = new Circle(directXRenderer, 5);
        Shape rectangle = new Rectangle(openGLRenderer, 10, 20);

        circle.draw();
        rectangle.draw();
    }
}
