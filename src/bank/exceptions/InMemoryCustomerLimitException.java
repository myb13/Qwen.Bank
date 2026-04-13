package bank.exceptions;

public class InMemoryCustomerLimitException extends BankingException{
    private static String MSG = "превышен лимит хранимых объектов customer: ";
    public InMemoryCustomerLimitException(int limit) {
        super(String.join("", MSG, Integer.toString(limit)));
    }

    public InMemoryCustomerLimitException(int limit, Throwable e) {
        super(String.join("", MSG, Integer.toString(limit)), e);
    }
}
