package bank.service;

import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.models.Account;
import bank.models.AccountStatus;
import bank.exceptions.InvalidAmountException;
import bank.exceptions.InsufficientFundsException;
import bank.exceptions.InvalidAccountStateException;

import java.math.BigDecimal;

public class AccountService {
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository){
        accountRepo = accountRepository;
        customerRepo = customerRepository;
    }

    public synchronized void transfer(Account from, Account to, BigDecimal amount)
            throws InsufficientFundsException, InvalidAccountStateException, InvalidAmountException {
        if (amount == null) throw new InvalidAmountException("сумма операции не указана");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidAmountException("сумма операции меньше 0");
        if (from.getStatus() != AccountStatus.ACTIVE) throw new InvalidAccountStateException("счёт списания неактивен");
        if (to.getStatus() != AccountStatus.ACTIVE) throw new InvalidAccountStateException("счёт зачисления неактивен");
        if (from.getBalance().compareTo(amount) < 0) throw new InsufficientFundsException(from.getBalance(), amount);

        // TODO: not transactional - if deposit() fails after withdraw(), funds are lost
        // Production fix: use two-phase commit or DB transactions
        from.withdraw(amount);
        to.deposit(amount);

        }
    }

