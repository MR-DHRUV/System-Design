import java.util.HashMap;
import java.util.Map;

class Position {
    public int x, y, z;
    
    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

// Flyweight Interface
interface Glyph {
    void render(Position pos); // Method to render a specific glyph at a specific position
}

// Concrete Flyweight Class
class CharacterGlyph implements Glyph {
    private final char character; // Intrinsic state: Immutable

    public CharacterGlyph(char character) {
        this.character = character;
    }

    @Override
    public void render(Position pos) {
        System.out.println(
                "Rendering character '" + character + "' at position (" + pos.x + ", " + pos.y + ", " + pos.z + ")");
    }
}

// Flyweight Factory: Caches and manages Glyph objects
class GlyphFactory {
    private final Map<Character, Glyph> glyphs = new HashMap<>();

    public Glyph getGlyph(char character) {
        return glyphs.computeIfAbsent(character, CharacterGlyph::new);
    }
}

// Context Class
class CharacterContext {

    Position pos; // Extrinsic state: Mutable
    private final Glyph glyph;

    public CharacterContext(Glyph glyph, Position pos) {
        this.glyph = glyph;
        this.pos = pos;
    }

    public void render() {
        glyph.render(pos);
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }
}


public class Main {
    public static void main(String[] args) {
        // Create a GlyphFactory to manage Glyph objects
        GlyphFactory factory = new GlyphFactory();

        // Create a context for each character
        CharacterContext c1 = new CharacterContext(factory.getGlyph('A'), new Position(0, 0, 0));
        CharacterContext c2 = new CharacterContext(factory.getGlyph('B'), new Position(1, 0, 0));
        CharacterContext c3 = new CharacterContext(factory.getGlyph('C'), new Position(2, 0, 0));

        // Render characters at their initial positions
        c1.render();
        c2.render();
        c3.render();

        // Change position of 'A' and render again
        c1.setPosition(new Position(5, 5, 5));
        c1.render();
    }
}
