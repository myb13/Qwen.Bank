package bank.repository;

import bank.exceptions.InMemoryCustomerLimitException;
import bank.exceptions.InMemoryCustomerNotFoundException;
import bank.exceptions.InvalidCustomerDataException;
import bank.models.Customer;

public class InMemoryCustomerRepository implements CustomerRepository {
    private static int MAX_ENTITIES = 100;
    private static int current_entities;
    private static Customer[] customers;

    public InMemoryCustomerRepository() {
        customers = new Customer[MAX_ENTITIES];
        current_entities = 0;
    }

    @Override
    public void save(Customer customer) throws InMemoryCustomerLimitException, InvalidCustomerDataException {
        if (customer != null) {
            if (current_entities < MAX_ENTITIES) {
                customers[current_entities++] = customer;
            }
            else throw new InMemoryCustomerLimitException(MAX_ENTITIES);
        } else {
            throw new InvalidCustomerDataException("объект customer пуст");
        }
    }

    @Override
    public Customer findById(int id) throws InMemoryCustomerNotFoundException, InvalidCustomerDataException {
        return customers[getEntity(id)];
    }

    @Override
    public Customer[] findAll() {
        return customers;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            customers[getEntity(id)] = null;
            return true;
        } catch (InMemoryCustomerNotFoundException e) {
            return false;
        }
    }

    private int getEntity(int idx) throws InMemoryCustomerNotFoundException {
        for (int customerIdx = 0; customerIdx < customers.length; customerIdx++) {
            if (customers[customerIdx] != null && customers[customerIdx].getId() == idx) return customerIdx;
        }
        throw new InMemoryCustomerNotFoundException("id", idx);
    }
}