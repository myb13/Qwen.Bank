package bank.exceptions;

public class BankingExceptionWithReason extends BankingException {
    public BankingExceptionWithReason(String msg, String reason){
        super(String.join(" ", msg, reason == null ? "причина не указана" : reason));
    }

    public BankingExceptionWithReason(String msg, String reason, Throwable e){
        super(String.join(" ", msg, reason == null ? "причина не указана" : reason), e);
    }

}
