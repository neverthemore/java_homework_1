package main.java.service;

import main.java.domain.Transaction;
import main.java.repository.TransactionRepository;
import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void recordTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAccountTransactions(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}