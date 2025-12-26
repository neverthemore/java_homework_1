package main.java.service;

import main.java.domain.*;
import main.java.repository.*;

public class BankService {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final ReportService reportService;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public BankService() {
        CustomerRepository customerRepo = new CustomerRepository();
        AccountRepository accountRepo = new AccountRepository();
        TransactionRepository transactionRepo = new TransactionRepository();

        this.customerService = new CustomerService(customerRepo);
        this.accountService = new AccountService(accountRepo, transactionRepo);
        this.reportService = new ReportService(accountRepo, transactionRepo);
        this.customerRepository = customerRepo;
        this.accountRepository = accountRepo;
    }

    // Фасадные методы
    public Customer createCustomer(String fullName) {
        return customerService.createCustomer(fullName);
    }

    public Account openDebitAccount(Customer owner) {
        return accountService.openDebitAccount(owner);
    }

    public Account openCreditAccount(Customer owner, double creditLimit) {
        return accountService.openCreditAccount(owner, creditLimit);
    }

    public Account findAccount(String accountNumber) {
        return accountService.findAccount(accountNumber).orElse(null);
    }

    public boolean deposit(String accountNumber, double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    public boolean withdraw(String accountNumber, double amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    public boolean transfer(String from, String to, double amount) {
        return accountService.transfer(from, to, amount);
    }

    public void printCustomerAccounts(int customerId) {
        reportService.printCustomerAccounts(customerId);
    }

    public void printTransactions() {
        reportService.printAllTransactions();
    }

    public void printReport() {
        reportService.printBankReport();
    }

    public Customer findCustomerById(int id) {
        return customerService.findCustomerById(id);
    }

    public boolean customerExists(int id) {
        return customerRepository.findById(String.valueOf(id)).isPresent();
    }
}