package bank.repository;
import bank.exceptions.*;
import bank.models.*;

public class InMemoryAccountRepository implements AccountRepository{
    private static int MAX_ENTITIES = 100;
    private static int current_entities;
    private static Account[] accounts;

    public InMemoryAccountRepository(){
        accounts = new Account[MAX_ENTITIES];
        current_entities = 0;
    }
    public void save(Account account) throws InMemoryAccountLimitException, InvalidAccountDataException {
        if (account != null) {
            if (current_entities < MAX_ENTITIES) accounts[++current_entities] = account;
            else throw new InMemoryAccountLimitException(MAX_ENTITIES);
        } else {throw new InvalidAccountDataException("объект account");}
    }

    @Override
    public Account findByNum(String number) throws InMemoryAccountNotFoundException {
        return accounts[getEntity(number)];
    }

    @Override
    public Account[] findAll() {
        return accounts;
    }

    @Override
    public boolean deleteByNum(String number) {
        try {
            accounts[getEntity(number)] = null;
            return true;
        } catch (InMemoryAccountNotFoundException e) {
            return false;
        }
    }

    @Override
    public Account[] findByCustomerId(int customerId) throws InMemoryCustomerNotFoundException, InvalidCustomerDataException {
        InMemoryCustomerRepository customer = new InMemoryCustomerRepository();
        return customer.findById(customerId).getAccounts();
    }

    private int getEntity (String number) throws InMemoryAccountNotFoundException {
        for(int accountIdx = 0; accountIdx < accounts.length; accountIdx++){
            if (accounts[accountIdx] != null && accounts[accountIdx].getNumber() == number) return accountIdx;
        }
        throw new InMemoryAccountNotFoundException("number", number);
    }
}
