package main.java.domain;

public abstract class Account {

    private final String accountNumber;
    private double balance;
    private final Customer owner;

    public Account(String accountNumber, double initialBalance, Customer owner) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.owner = owner;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getOwner() {
        return owner;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean transfer(Account to, double amount) {
        if (amount <= 0) {
            return false;
        }

        if (this.withdraw(amount)) {
            if (to.deposit(amount)) {
                return true;
            } else {
                this.deposit(amount);
                return false;
            }
        }
        return false;
    }

    public abstract String getAccountType();

    @Override
    public String toString() {
        return String.format("Счет: %s, Тип: %s, Баланс: %.2f, Владелец: %s",
                accountNumber, getAccountType(), balance, owner.getFullName());
    }
}


