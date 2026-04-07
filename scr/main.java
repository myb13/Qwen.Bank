import ru.bank.models.*;
void main(){
    Customer cus = new Customer("MikHail", "ivanOV", "denisovich");
    Account acc1 = new Account(cus, 1000.0);
    Account acc2 = new Account(cus, 22000.0);

    System.out.println("Customer ID: " + cus.getId() + ", full name: " + cus.fullName());
    cus.accountsInfo();

    acc1.withdraw(15000);
    acc2.deposit(1000);
    cus.accountsInfo();

    acc1.withdraw(15000);
    acc2.close();
    cus.accountsInfo();

    acc1.deposit(133);
    acc2.withdraw(23000);
    acc2.close();
    cus.accountsInfo();

}