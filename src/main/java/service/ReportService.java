package main.java.service;

import main.java.domain.*;
import main.java.repository.AccountRepository;
import main.java.repository.TransactionRepository;
import java.util.List;

public class ReportService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public ReportService(AccountRepository accountRepository,
                         TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void printCustomerAccounts(int customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (accounts.isEmpty()) {
            System.out.println("У клиента с ID " + customerId + " нет счетов.");
        } else {
            System.out.println("\n=== Счета клиента ID: " + customerId + " ===");
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }

    public void printAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            System.out.println("Нет транзакций.");
        } else {
            System.out.println("\n=== Все транзакции ===");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    public void printBankReport() {
        List<Account> accounts = accountRepository.findAll();
        List<Transaction> transactions = transactionRepository.findAll();

        int debitCount = 0;
        int creditCount = 0;
        double debitTotal = 0;
        double creditTotal = 0;
        long successfulTransactions = 0;
        long failedTransactions = 0;

        for (Account account : accounts) {
            if (account instanceof DebitAccount) {
                debitCount++;
                debitTotal += account.getBalance();
            } else if (account instanceof CreditAccount) {
                creditCount++;
                creditTotal += account.getBalance();
            }
        }

        for (Transaction t : transactions) {
            if (t.isSuccess()) {
                successfulTransactions++;
            } else {
                failedTransactions++;
            }
        }

        System.out.println("\n=== ОТЧЕТ БАНКА ===");
        System.out.println("Дебетовые счета:");
        System.out.println("  Количество: " + debitCount);
        System.out.println("  Суммарный баланс: " + debitTotal);
        System.out.println("\nКредитные счета:");
        System.out.println("  Количество: " + creditCount);
        System.out.println("  Суммарный баланс: " + creditTotal);
        System.out.println("\nОперации:");
        System.out.println("  Успешных: " + successfulTransactions);
        System.out.println("  Неуспешных: " + failedTransactions);
        System.out.println("  Всего: " + transactions.size());
    }
}


