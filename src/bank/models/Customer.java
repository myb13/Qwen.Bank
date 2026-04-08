package bank.models;

import java.util.Arrays;
import java.util.Locale;

public class Customer {
    private static int MAX_ACC_QTY = 5;
    private static int idCustomers = 0;
    private int id; // ToDo not thread-safe
    private String firstName, middleName, lastName;
    private Account[] accounts;

    public Customer(String firstName, String lastName, String middleName)
    throws IllegalArgumentException{
        if(firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = formatName(firstName);
        this.lastName = formatName(lastName);
        this.middleName = formatName(middleName);
        accounts = new Account[MAX_ACC_QTY];
        id = getIdCustomers();
    }

    public int getId() {
        return id;
    }

    public String fullName(){
        return String.join(" ", lastName, firstName, middleName);
    }

    public int getAccountsQty(){
        int qty = 0;
        for(var account : accounts) {
            if (account != null) qty++;
            else break;
        }
        return qty;
    }

    public void associateAccount(Account account)
    throws IllegalArgumentException{
        boolean notAssociated = true;
        if (account != null) {
            for (int i = 0; i < MAX_ACC_QTY; i++) {
                if (accounts[i] == null) {
                    accounts[i] = account;
                    notAssociated = false;
                    break;
                }
            }
        }
        if (notAssociated) throw new IllegalArgumentException();
    }

    public Account[] getAccounts() {
        Account[] accountsCopy = Arrays.copyOf(accounts, accounts.length);
        return accountsCopy;
    }

    public void accountsInfo(){
        for (var account : accounts) {
            if (account != null) System.out.printf("Account " + account.getNumber() +
                    " type: " + account.getType() + " state: " + account.getStatus() + ", balance: %.2f\n", account.getBalance());
            else break;
        }
    }

    private String formatName(String name){
        return String.join("", name.trim().substring(0, 1).toUpperCase(Locale.ROOT), name.trim().substring(1).toLowerCase(Locale.ROOT));
    }

    private static int getIdCustomers(){
        return ++idCustomers;
    }
}
