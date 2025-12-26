package main.java.menu;

import main.java.domain.Account;
import main.java.domain.Customer;
import main.java.service.BankService;
import java.util.Scanner;

public class ConsoleMenu {
    private final BankService bankService;
    private final Scanner scanner;

    public ConsoleMenu(BankService bankService) {
        this.bankService = bankService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = readInt("Выберите пункт меню: ");
            handleChoice(choice);
        }
    }

    private void printMenu() {
        System.out.println("\n=== БАНКОВСКАЯ СИСТЕМА ===");
        System.out.println("1. Создать клиента");
        System.out.println("2. Открыть дебетовый счёт");
        System.out.println("3. Открыть кредитный счёт");
        System.out.println("4. Пополнить счёт");
        System.out.println("5. Снять со счёта");
        System.out.println("6. Перевести между счетами");
        System.out.println("7. Показать счета клиента");
        System.out.println("8. Показать все транзакции");
        System.out.println("9. Отчёт банка");
        System.out.println("10. Выход");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> createCustomer();
            case 2 -> openDebitAccount();
            case 3 -> openCreditAccount();
            case 4 -> deposit();
            case 5 -> withdraw();
            case 6 -> transfer();
            case 7 -> printCustomerAccounts();
            case 8 -> printTransactions();
            case 9 -> printReport();
            case 10 -> {
                System.out.println("Выход из программы.");
                System.exit(0);
            }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void createCustomer() {
        System.out.print("Введите ФИО клиента: ");
        String fullName = scanner.nextLine();
        Customer customer = bankService.createCustomer(fullName);
        System.out.println("Создан клиент: " + customer);
    }

    private void openDebitAccount() {
        int customerId = readInt("Введите ID клиента: ");
        Customer customer = bankService.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Клиент не найден!");
            return;
        }
        Account account = bankService.openDebitAccount(customer);
        System.out.println("Открыт дебетовый счет: " + account);
    }

    private void openCreditAccount() {
        int customerId = readInt("Введите ID клиента: ");
        Customer customer = bankService.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Клиент не найден!");
            return;
        }
        double creditLimit = readDouble("Введите кредитный лимит: ");
        Account account = bankService.openCreditAccount(customer, creditLimit);
        System.out.println("Открыт кредитный счет: " + account);
    }

    private void deposit() {
        System.out.print("Введите номер счета: ");
        String accountNumber = scanner.nextLine();
        double amount = readDouble("Введите сумму для пополнения: ");
        boolean success = bankService.deposit(accountNumber, amount);
        System.out.println(success ? "Пополнение успешно!" : "Ошибка пополнения.");
    }

    private void withdraw() {
        System.out.print("Введите номер счета: ");
        String accountNumber = scanner.nextLine();
        double amount = readDouble("Введите сумму для снятия: ");
        boolean success = bankService.withdraw(accountNumber, amount);
        System.out.println(success ? "Снятие успешно!" : "Ошибка снятия.");
    }

    private void transfer() {
        System.out.print("Введите номер счета отправителя: ");
        String from = scanner.nextLine();
        System.out.print("Введите номер счета получателя: ");
        String to = scanner.nextLine();
        double amount = readDouble("Введите сумму для перевода: ");
        boolean success = bankService.transfer(from, to, amount);
        System.out.println(success ? "Перевод успешен!" : "Ошибка перевода.");
    }

    private void printCustomerAccounts() {
        int customerId = readInt("Введите ID клиента: ");
        bankService.printCustomerAccounts(customerId);
    }

    private void printTransactions() {
        bankService.printTransactions();
    }

    private void printReport() {
        bankService.printReport();
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка! Введите целое число.");
            scanner.next();
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return value;
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Ошибка! Введите число.");
            scanner.next();
            System.out.print(prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        return value;
    }
}


