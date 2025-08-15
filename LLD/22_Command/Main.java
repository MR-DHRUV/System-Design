// Command interface
interface Command {
    void execute();
}

// Reciever: that will receive commands and do the actual work
public class TextEditor {

    public void copy() {
        System.out.println("Copying selected text to clipboard.");
    }

    public void paste() {
        System.out.println("Pasting text from clipboard.");
    }
}

// Concrete Commands
public class CopyCommand implements Command {

    private final TextEditor editor;

    public CopyCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.copy();
    }
}

public class PasteCommand implements Command {

    private final TextEditor editor;

    public PasteCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.paste();
    }
}

// Invoker
class Button {
    private String label;
    private Command command;

    public Button(String label, Command command) {
        this.label = label;
        this.command = command;
    }

    public void click() {
        System.out.println("Button '" + label + "' clicked.");
        command.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        
    }
}
