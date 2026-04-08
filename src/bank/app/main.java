import bank.exceptions.BankingException;
import bank.exceptions.InvalidAccountStateException;
import bank.exceptions.InvalidAmountException;
import bank.models.Account;
import bank.models.AccountType;
import bank.models.Customer;

public static void main(String[] args){
    Customer cus;
    Account acc1, acc2;
    try {
        cus = new Customer("MikHail", "ivanOV", null);
        System.out.println("Customer ID: " + cus.getId() + ", full name: " + cus.fullName());

        try {
            acc1 = new Account(cus, AccountType.CHECKING, new BigDecimal(1000));
            acc2 = new Account(cus, AccountType.SAVINGS, new BigDecimal(22000));

            try {acc1.withdraw(new BigDecimal(15000));} catch (BankingException e) {System.out.println(e.getMessage());}
            try {acc2.deposit(new BigDecimal(1000));} catch (BankingException e) {System.out.println(e.getMessage());}
            cus.accountsInfo();

            try {acc1.withdraw(new BigDecimal(15000));} catch (BankingException e) {System.out.println(e.getMessage());}
            try {acc2.close();} catch (BankingException e) {e.getMessage();}
            cus.accountsInfo();

            try {acc1.deposit(new BigDecimal(133));} catch (BankingException e) {System.out.println(e.getMessage());}
            try {acc2.withdraw(new BigDecimal(23000));} catch (BankingException e) {System.out.println(e.getMessage());}
            try { acc2.close();} catch (BankingException e) {System.out.println(e.getMessage());}
            cus.accountsInfo();
        } catch (BankingException e) {System.out.println(e.getMessage());}
    } catch (BankingException e) {System.out.println(e.getMessage());}
}