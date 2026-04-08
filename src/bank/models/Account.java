package bank.models;

import java.math.BigDecimal;

public class Account {
    private String number;
    private Customer owner;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;

    public Account(Customer owner, AccountType type)
    throws IllegalArgumentException{
        if (owner == null || type == null) throw new IllegalArgumentException();
        this.owner = owner;
        this.number = numberGenerate();
        this.balance = new BigDecimal(0);
        this.status = AccountStatus.ACTIVE;
        this.type = type;
        owner.associateAccount(this);
    }

    public Account(Customer owner, AccountType type, BigDecimal balance){
        this(owner, type);
        this.balance = balance;
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
    throws IllegalArgumentException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException();
        else balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount)
    throws IllegalArgumentException{
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 || balance.compareTo(amount) >= 0)
            balance = balance.subtract(amount);
        else throw new IllegalArgumentException();
    }

    public void close()
    throws IllegalArgumentException{
        if (status != AccountStatus.CLOSED && balance.compareTo(BigDecimal.ZERO) == 0) status = AccountStatus.CLOSED;
        else throw new IllegalArgumentException();
    }

    private String numberGenerate(){
        String ids = Integer.toString(owner.getId());
        String acc = Integer.toString(owner.getAccountsQty() + 1);
        return String.join("", "42306", "0".repeat(3 - acc.length()), acc, "0".repeat(6 - ids.length()), ids);
    }
}
