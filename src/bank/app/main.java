import bank.exceptions.BankingException;
import bank.exceptions.InvalidAccountStateException;
import bank.exceptions.InvalidAmountException;
import bank.models.Account;
import bank.models.AccountType;
import bank.models.Customer;
import bank.repository.InMemoryAccountRepository;
import bank.repository.InMemoryCustomerRepository;

public static void main(String[] args){
    Customer cus, cus2;
    Account acc1, acc2, acc3, acc4, acc5, acc6;
    InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
    InMemoryCustomerRepository customerRepository = new InMemoryCustomerRepository();
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
            acc4 = new Account(cus, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc4);
            acc5 = new Account(cus2, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc5);
            acc6 = new Account(cus2, AccountType.SAVINGS, new BigDecimal(22000));
            accountRepository.save(acc6);

            cus.accountsInfo();
            System.out.println();
            cus2.accountsInfo();
            System.out.println();
            System.out.println("found cus in repo: " + customerRepository.findById(1).fullName());
            customerRepository.deleteById(1);
            System.out.println("found cus in repo: " + customerRepository.findById(1).fullName());
        } catch (BankingException e) {System.out.println(e.getMessage());}
    } catch (BankingException e) {System.out.println(e.getMessage());}
}