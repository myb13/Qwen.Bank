import bank.exceptions.BankingException;
import bank.exceptions.InvalidAccountStateException;
import bank.exceptions.InvalidAmountException;
import bank.models.Account;
import bank.models.AccountType;
import bank.models.Customer;
import bank.repository.InMemoryAccountRepository;
import bank.repository.InMemoryCustomerRepository;
import bank.service.AccountService;

public static void main(String[] args){
    Customer cus, cus2;
    Account acc1, acc2, acc3, acc4, acc5, acc6;
    InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
    InMemoryCustomerRepository customerRepository = new InMemoryCustomerRepository();
    AccountService bankTransfer = new AccountService(accountRepository, customerRepository);
    try {
        cus = new Customer("MikHail", "ivanOV", null);
        customerRepository.save(cus);
        cus2 = new Customer("Oleg", "Redkin", "Petrovich");
        customerRepository.save(cus2);
        System.out.println("Customer ID: " + cus.getId() + ", full name: " + cus.fullName());
        System.out.println("Customer ID: " + cus2.getId() + ", full name: " + cus2.fullName());

        try {
            acc1 = new Account(cus, AccountType.CHECKING, new BigDecimal(1000));
            accountRepository.save(acc1);
            acc2 = new Account(cus, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc2);
            acc3 = new Account(cus, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc3);
            acc4 = new Account(cus2, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc4);
            acc5 = new Account(cus2, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc5);
            acc6 = new Account(cus2, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc6);

            System.out.printf("Баланс до. Счёт1: %.2f, Счёт2: %.2f\n", acc1.getBalance().doubleValue(), acc4.getBalance().doubleValue());
            bankTransfer.transfer(acc1, acc4, BigDecimal.valueOf(1000.0));
            System.out.printf("Баланс после. Счёт1: %.2f, Счёт2: %.2f\n", acc1.getBalance().doubleValue(), acc4.getBalance().doubleValue());
            try {
                System.out.println("Попытка перевода со счета 1: ");
                bankTransfer.transfer(acc1, acc2, BigDecimal.valueOf(500.5));}
            catch (BankingException e) {System.out.println(e.getMessage());}

            acc1.close();
            try {
                System.out.println("Попытка перевода со счета 1: ");
                bankTransfer.transfer(acc1, acc2, BigDecimal.valueOf(500.5));}
            catch (BankingException e) {System.out.println(e.getMessage());}
        } catch (BankingException e) {System.out.println(e.getMessage());}
    } catch (BankingException e) {System.out.println(e.getMessage());}
}