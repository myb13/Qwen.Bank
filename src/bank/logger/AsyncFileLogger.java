package bank.logger;

public class AsyncFileLogger implements Logger{
    //TODO use java.util.concurrent.BlockingQueue
    private static String LOG_NAME = "current.log";

    public void info(String message) {
        Tools.put(LOG_NAME, String.join("", "[info] ", message));
    }

    @Override
    public void error(String message, Throwable t) {
        Tools.put(LOG_NAME, String.join(" ", "[error]", message+",", "exception:", t.getMessage()));
    }
}
