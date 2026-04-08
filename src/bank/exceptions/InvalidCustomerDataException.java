package bank.exceptions;

public class InvalidCustomerDataException extends BankingExceptionWithReason{
    private static String MSG = "ошибка данных по клиенту: ";

    public InvalidCustomerDataException(String reason){
        super(MSG, reason);
    }

    public InvalidCustomerDataException(String reason, Throwable e){
        super(MSG, reason, e);
    }
}