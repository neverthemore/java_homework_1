package main.java;

import main.java.menu.ConsoleMenu;
import main.java.service.BankService;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        ConsoleMenu menu = new ConsoleMenu(bankService);

        System.out.println("Добро пожаловать в банковскую систему!");
        menu.run();
    }
}



