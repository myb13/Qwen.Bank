package bank.exceptions;

public class InvalidAccountStateException extends BankingExceptionWithReason{
    private static String MSG = "Невозможно закрыть счет: ";

    public InvalidAccountStateException(String reason){
        super(MSG, reason);
    }

    public InvalidAccountStateException(String reason, Throwable e){
        super(MSG, reason, e);
    }
}