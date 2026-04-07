package ru.bank.models;

import java.util.Locale;

public class Customer {
    private static int idCustomers = 0;
    private int id;
    private String firstName, middleName, lastName;
    private Account[] accounts;

    public Customer(String firstName, String lastName, String middleName){
        this.firstName = formatName(firstName);
        this.lastName = formatName(lastName);
        this.middleName = formatName(middleName);
        id = getIdCustomers();
    }

    public int getId() {
        return id;
    }

    public String fullName(){
        return String.join(" ", lastName, firstName, middleName);
    }

    public int getAccountsQty(){
        return accounts == null ? 0 : accounts.length;
    }

    public void associateAccount(Account account){
        if(accounts != null){
            Account[] tempAccounts = new Account[accounts.length + 1];
            for(int i = 0; i < accounts.length; i++) tempAccounts[i] = accounts[i];
            tempAccounts[accounts.length] = account;
            accounts = tempAccounts;
        } else {
            accounts = new Account[1];
            accounts[0] = account;
        }
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void accountsInfo(){
        for (var account : accounts) System.out.printf("Account " + account.getNumber() + " state: " + account.getState() + ", balance: %.2f\n", account.getBalance());
    }

    private String formatName(String name){
        return String.join("", name.trim().substring(0, 1).toUpperCase(Locale.ROOT), name.trim().substring(1).toLowerCase(Locale.ROOT));
    }

    private static int getIdCustomers(){
        return ++idCustomers;
    }
}
