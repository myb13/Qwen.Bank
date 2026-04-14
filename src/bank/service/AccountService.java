package bank.service;

import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.models.*;
import bank.exceptions.*;

import java.math.BigDecimal;

public class AccountService {
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository){
        accountRepo = accountRepository;
        customerRepo = customerRepository;
    }

    public synchronized void transfer(Account from, Account to, BigDecimal amount)
            throws InsufficientFundsException, InvalidAccountStateException, BankingException {
        if (amount == null) throw new InvalidAmountException("сумма операции не указана");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidAmountException("сумма операции меньше 0");
        if (from.getStatus() != AccountStatus.ACTIVE) throw new InvalidAccountStateException("счёт списания неактивен");
        if (to.getStatus() != AccountStatus.ACTIVE) throw new InvalidAccountStateException("счёт зачисления неактивен");
        if (from.getBalance().compareTo(amount) < 0) throw new InsufficientFundsException(from.getBalance(), amount);

        from.withdraw(amount);
        to.deposit(amount);

        }
    }

