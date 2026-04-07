package ru.bank.models;

public class Account {
    private String number;
    private Customer owner;
    private double balance;
    private AccountStates state;

    public Account(Customer owner){
        this.owner = owner;
        this.number = numberGenerate();
        this.balance = 0.0;
        this.state = AccountStates.ACTIVE;
        owner.associateAccount(this);
    }

    public Account(Customer owner, double balance){
        this(owner);
        deposit(balance);
    }

    public Customer getOwner() {
        return owner;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public AccountStates getState() {
        return state;
    }

    public void deposit(double amount) {
        this.balance += amount > 0 ? amount : 0;
    }

    public void withdraw(double amount){
        balance -= balance >= (amount > 0 ? amount : 0) ? amount : 0;
    }

    public void close() {
        if (state != AccountStates.CLOSED && balance == 0.0) state = AccountStates.CLOSED;
    }

    private String numberGenerate(){
        String ids = Integer.toString(owner.getId());
        String acc = Integer.toString(owner.getAccountsQty() + 1);
        return String.join("", "42306", "0".repeat(3 - acc.length()), acc, "0".repeat(6 - ids.length()), ids);
    }
}
