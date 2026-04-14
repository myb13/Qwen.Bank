package bank.repository;
import bank.exceptions.*;
import bank.models.*;

public interface AccountRepository {
    void save(Account account) throws InMemoryAccountLimitException, InvalidAccountDataException;                    // INSERT/UPSERT
    Account findByNum(String number) throws InMemoryAccountNotFoundException;                       // SELECT WHERE id = ?
    Account[] findAll();                            // SELECT *
    boolean deleteByNum(String number);                      // DELETE WHERE id = ?
    Account[] findByCustomerId(int customerId) throws InMemoryCustomerNotFoundException, InvalidCustomerDataException;      // SELECT * WHERE customer_id = ?
}
