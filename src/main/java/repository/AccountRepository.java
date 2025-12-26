package main.java.repository;

import main.java.domain.Account;
import java.util.*;

public class AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1;

    public Account save(Account account) {
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public Optional<Account> findByNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    public List<Account> findByCustomerId(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getOwner().getId() == customerId) {
                result.add(account);
            }
        }
        return result;
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public String generateAccountNumber() {
        return String.format("ACC%08d", nextAccountNumber++);
    }
}


