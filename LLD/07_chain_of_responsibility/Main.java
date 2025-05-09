// abstract handler
abstract class Logger {
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (canHandle(level)) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        } else { // Default handler
            System.out.println("No logger available for level: " + level);
        }
    }

    // Builds a chain of responsibility
    public static Logger link(Logger first, Logger ...chain)
    {
        Logger current = first;
        for (Logger logger : chain) {
            current.setNextLogger(logger);
            current = logger;
        }
        return first;
    }

    protected abstract boolean canHandle(int level);
    protected abstract void write(String message);
}

// Concrete handlers
class InfoLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        return level == 1;
    }

    @Override
    protected void write(String message) {
        System.out.println("Info: " + message);
    }
}

class DebugLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        return level == 2;
    }

    @Override
    protected void write(String message) {
        System.out.println("Debug: " + message);
    }
}

class ErrorLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        return level == 3;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error: " + message);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Logger ApplicationLogger = Logger.link(
            new InfoLogger(),
            new DebugLogger(),
            new ErrorLogger()
        );
    }
};
