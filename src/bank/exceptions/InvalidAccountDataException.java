package bank.exceptions;

public class InvalidAccountDataException extends BankingExceptionWithReason{
    private static String MSG = "ошибка данных по счёту: ";

    public InvalidAccountDataException(String reason){
        super(MSG, reason);
    }

    public InvalidAccountDataException(String reason, Throwable e){
        super(MSG, reason, e);
    }
}