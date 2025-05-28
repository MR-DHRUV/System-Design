class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger initialized");
    }

    // synchronized will make it thread-safe
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}


class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("This is the first log message.");

        Logger logger2 = Logger.getInstance();
        logger2.log("This is the second log message.");

        // Verify that both logger1 and logger2 are the same instance
        System.out.println(logger1 == logger2);
    }
}
