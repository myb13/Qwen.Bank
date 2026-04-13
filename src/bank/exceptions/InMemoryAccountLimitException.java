package bank.exceptions;

public class InMemoryAccountLimitException extends BankingException{
    private static String MSG = "превышен лимит хранимых объектов account: ";
    public InMemoryAccountLimitException(int limit) {
        super(String.join("", MSG, Integer.toString(limit)));
    }
    public InMemoryAccountLimitException(int limit, Throwable e) {
        super(String.join("", MSG, Integer.toString(limit)), e);
    }
}
