package main.java.service;

import main.java.domain.Customer;
import main.java.repository.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String fullName) {
        int id = customerRepository.generateId();
        Customer customer = new Customer(id, fullName);
        customerRepository.save(customer);
        return customer;
    }

    public Customer findCustomerById(int id) {
        return customerRepository.findById(String.valueOf(id)).orElse(null);
    }
}