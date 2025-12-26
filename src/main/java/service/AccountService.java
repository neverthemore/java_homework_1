package main.java.service;

import main.java.domain.*;
import main.java.repository.AccountRepository;
import main.java.repository.TransactionRepository;
import java.util.Optional;

public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account openDebitAccount(Customer owner) {
        String accountNumber = accountRepository.generateAccountNumber();
        Account account = new DebitAccount(accountNumber, owner, 0.0);
        accountRepository.save(account);
        return account;
    }

    public Account openCreditAccount(Customer owner, double creditLimit) {
        String accountNumber = accountRepository.generateAccountNumber();
        Account account = new CreditAccount(accountNumber, owner, 0.0, creditLimit);
        accountRepository.save(account);
        return account;
    }

    public Optional<Account> findAccount(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }

    public boolean deposit(String accountNumber, double amount) {
        Optional<Account> accountOpt = accountRepository.findByNumber(accountNumber);
        if (accountOpt.isEmpty()) {
            transactionRepository.save(new Transaction(
                    TransactionType.DEPOSIT, amount, null, accountNumber,
                    false, "Счет не найден"
            ));
            return false;
        }

        Account account = accountOpt.get();
        boolean success = account.deposit(amount);

        transactionRepository.save(new Transaction(
                TransactionType.DEPOSIT, amount, null, accountNumber,
                success, success ? "OK" : "Сумма должна быть положительной"
        ));

        return success;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Optional<Account> accountOpt = accountRepository.findByNumber(accountNumber);
        if (accountOpt.isEmpty()) {
            transactionRepository.save(new Transaction(
                    TransactionType.WITHDRAW, amount, accountNumber, null,
                    false, "Счет не найден"
            ));
            return false;
        }

        Account account = accountOpt.get();
        boolean success = account.withdraw(amount);

        transactionRepository.save(new Transaction(
                TransactionType.WITHDRAW, amount, accountNumber, null,
                success, success ? "OK" : "Недостаточно средств или неверная сумма"
        ));

        return success;
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Optional<Account> fromOpt = accountRepository.findByNumber(fromAccountNumber);
        Optional<Account> toOpt = accountRepository.findByNumber(toAccountNumber);

        if (fromOpt.isEmpty() || toOpt.isEmpty()) {
            transactionRepository.save(new Transaction(
                    TransactionType.TRANSFER, amount, fromAccountNumber, toAccountNumber,
                    false, "Один из счетов не найден"
            ));
            return false;
        }

        Account from = fromOpt.get();
        Account to = toOpt.get();

        boolean success = from.transfer(to, amount);

        transactionRepository.save(new Transaction(
                TransactionType.TRANSFER, amount, fromAccountNumber, toAccountNumber,
                success, success ? "OK" : "Ошибка перевода"
        ));

        return success;
    }
}