package bank.exceptions;

public class InMemoryAccountNotFoundException extends BankingExceptionWithReason {
    private static String MSG = "не удалось найти account по параметру ";
    public InMemoryAccountNotFoundException(String reason, String number) {
        super(MSG, String.join(" ", reason, number));
    }
    public InMemoryAccountNotFoundException(String reason, String number, Throwable e) {
        super(MSG, String.join(" ", reason, number), e);
    }
}
