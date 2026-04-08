package bank.exceptions;

public class InvalidAmountException extends BankingException{
    private static String MSG = "Некорректная сумма операции";
    public InvalidAmountException(){
        super(MSG);
    }
    public InvalidAmountException(Throwable e){
        super(MSG, e);
    }
}
