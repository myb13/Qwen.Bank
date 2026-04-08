package bank.exceptions;

public class AccountLimitExceededException extends BankingExceptionWithReason{
    private static String MSG = "превышено допустимое количество открытых счетов: ";

    public AccountLimitExceededException(int qty){
        super(MSG, Integer.toString(qty));
    }

    public AccountLimitExceededException(int qty, Throwable e){
        super(MSG, Integer.toString(qty), e);
    }
}