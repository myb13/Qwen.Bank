package bank.logger;

public interface Logger {
    void info(String message);
    void error(String message, Throwable t);
}