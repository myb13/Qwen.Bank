import bank.models.Account;
import bank.models.AccountType;
import bank.models.Customer;

public static void main(String[] args){
    Customer cus = new Customer("MikHail", "ivanOV", null);
    Account acc1 = new Account(cus, AccountType.CHECKING, new BigDecimal(1000));
    Account acc2 = new Account(cus, AccountType.SAVINGS, new BigDecimal(22000));

    System.out.println("Customer ID: " + cus.getId() + ", full name: " + cus.fullName());
    cus.accountsInfo();

    try {acc1.withdraw(new BigDecimal(15000));} catch (IllegalArgumentException e) {
        System.out.println("operation error");
    }
    acc2.deposit(new BigDecimal(1000));
    cus.accountsInfo();

    try {acc1.withdraw(new BigDecimal(15000));} catch (IllegalArgumentException e) {
        System.out.println("operation error");
    }

    try {acc2.close();} catch (IllegalArgumentException e) {
        System.out.println("operation error");
    }
    cus.accountsInfo();

    acc1.deposit(new BigDecimal(133));
    acc2.withdraw(new BigDecimal(23000));
    acc2.close();
    cus.accountsInfo();

}