package main.java.repository;

import main.java.domain.Transaction;
import java.util.*;

public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> findByAccountNumber(String accountNumber) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if ((t.getFromAccountNumber() != null && t.getFromAccountNumber().equals(accountNumber)) || (t.getToAccountNumber() != null && t.getToAccountNumber().equals(accountNumber))) {
                result.add(t);
            }
        }
        return result;
    }
}
