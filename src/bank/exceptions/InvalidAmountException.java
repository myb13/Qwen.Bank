package bank.exceptions;

public class InvalidAmountException extends BankingExceptionWithReason{
    private static String MSG = "Некорректная сумма операции";
    public InvalidAmountException(String reason){
        super(MSG, reason);
    }
    public InvalidAmountException(String reason, Throwable e){
        super(MSG, reason, e);
    }
}
