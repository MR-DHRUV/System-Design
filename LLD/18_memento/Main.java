import java.util.Stack;

// Editor state
class EditorState {
    private final String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Memento
class EditorMemento {
    private final EditorState state; // cant be modified once created

    public EditorMemento(EditorState state) {
        this.state = state;
    }

    public EditorState getState() {
        return state;
    }
}

// Originator
class Editor {
    private EditorState state;

    public void setContent(EditorState state) {
        this.state = state;
    }

    public EditorState getContent() {
        return state;
    }

    public EditorMemento save() {
        return new EditorMemento(state);
    }

    public void restore(EditorMemento memento) {
        this.state = memento.getState();
    }
}

// Caretaker
class EditorHistory {
    private final Stack<EditorMemento> history = new Stack<>();

    public void save(EditorMemento memento) {
        history.push(memento);
    }

    public EditorMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }

        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        EditorHistory history = new EditorHistory();

        editor.setContent(new EditorState("Version 1"));
        history.save(editor.save());

        editor.setContent(new EditorState("Version 2"));
        history.save(editor.save());

        editor.setContent(new EditorState("Version 3"));

        System.out.println("Current: " + editor.getContent().getContent());

        editor.restore(history.undo());
        System.out.println("After undo: " + editor.getContent().getContent());

        editor.restore(history.undo());
        System.out.println("After second undo: " + editor.getContent().getContent());
    }
}