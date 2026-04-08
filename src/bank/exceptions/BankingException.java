package bank.exceptions;

public class BankingException extends Exception {
    Throwable cause;
    public BankingException(String message) {
        super(message);
    }
    public BankingException(String message, Throwable cause) {
        super(message, cause);
    };
}
