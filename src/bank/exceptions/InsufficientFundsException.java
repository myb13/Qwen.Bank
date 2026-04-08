package bank.exceptions;

public class InsufficientFundsException extends BankingException{
    private static String MSG = "Недостаточно денежных средств";
    public InsufficientFundsException(){
        super(MSG);
    }
    public InsufficientFundsException(Throwable e){
        super(MSG, e);
    }
}
