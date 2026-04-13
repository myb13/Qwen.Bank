package bank.repository;
import bank.exceptions.InMemoryCustomerLimitException;
import bank.exceptions.InMemoryCustomerNotFoundException;
import bank.exceptions.InvalidCustomerDataException;
import bank.models.*;

public interface CustomerRepository
{
    void save(Customer customer) throws InMemoryCustomerLimitException, InvalidCustomerDataException;                    // INSERT/UPSERT
    Customer findById(int id) throws InMemoryCustomerNotFoundException, InvalidCustomerDataException;                       // SELECT WHERE id = ?
    Customer[] findAll();                            // SELECT *
    boolean deleteById(int id);                      // DELETE WHERE id = ?
}
