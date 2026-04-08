package bank.exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends BankingException{
    private static String MSG = "Недостаточно денежных средств.";
    BigDecimal balance, requestedAmount;
    public InsufficientFundsException(BigDecimal balance, BigDecimal requestedAmount){
        super(String.join(" ", MSG, "Баланс:", balance.toPlainString(), ", запрошено:", requestedAmount.toPlainString()));
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }
    public InsufficientFundsException(BigDecimal balance, BigDecimal requestedAmount, Throwable e){
        super(String.join(" ", MSG, "Баланс:", balance.toPlainString(), ", запрошено:", requestedAmount.toPlainString()), e);
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }
}
