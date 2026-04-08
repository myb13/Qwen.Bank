package bank.models;

import bank.exceptions.*;

import java.math.BigDecimal;

public class Account {
    private String number;
    private Customer owner;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;

    public Account(Customer owner, AccountType type)
    throws InvalidCustomerDataException, InvalidAccountDataException, AccountLimitExceededException{
        if(owner == null) throw new InvalidCustomerDataException("владелец счёта не указан");
        if(type == null) throw new InvalidAccountDataException("тип счёта");
        this.owner = owner;
        this.number = numberGenerate();
        this.balance = new BigDecimal(0);
        this.status = AccountStatus.ACTIVE;
        this.type = type;
        try {owner.associateAccount(this);} catch (AccountLimitExceededException e) {throw e;}
    }

    public Account(Customer owner, AccountType type, BigDecimal balance)
            throws InvalidCustomerDataException, InvalidAccountDataException, AccountLimitExceededException{
        this(owner, type);
        if(balance != null) this.balance = balance;
    }

    public Customer getOwner() {
        return owner;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public AccountType getType() {
        return type;
    }

    public void deposit(BigDecimal amount)
    throws InvalidAmountException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidAmountException();
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount)
            throws InvalidAmountException, InsufficientFundsException {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidAmountException();
        if(balance.compareTo(amount) < 0) throw new InsufficientFundsException();
        balance = balance.subtract(amount);
    }

    public void close()
    throws InvalidAccountStateException{
        if(status == AccountStatus.CLOSED) throw new InvalidAccountStateException("счёт уже закрыт");
        if(balance.compareTo(BigDecimal.ZERO) != 0)  throw new InvalidAccountStateException("баланс счёта ненулевой");
        status = AccountStatus.CLOSED;
    }

    private String numberGenerate(){
        String ids = Integer.toString(owner.getId());
        String acc = Integer.toString(owner.getAccountsQty() + 1);
        return String.join("", "42306", "0".repeat(3 - acc.length()), acc, "0".repeat(6 - ids.length()), ids);
    }
}
