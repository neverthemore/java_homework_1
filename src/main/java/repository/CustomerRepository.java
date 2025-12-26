package main.java.repository;

import main.java.domain.Customer;
import java.util.*;

public class CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();
    private int nextId = 1;

    public Customer save(Customer customer) {
        customers.put(String.valueOf(customer.getId()), customer);
        return customer;
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
    public int generateId() {
        return nextId++;
    }
}


