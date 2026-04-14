package bank.exceptions;

public class InMemoryCustomerNotFoundException extends BankingExceptionWithReason {
    private static String MSG = "не удалось найти customer по параметру ";
    public InMemoryCustomerNotFoundException(String reason, int id) {
        super(MSG, String.join(" ", reason, Integer.toString(id)));
    }

    public InMemoryCustomerNotFoundException(String reason, int id, Throwable e) {
        super(MSG, String.join(" ", reason, Integer.toString(id)), e);
    }
}
