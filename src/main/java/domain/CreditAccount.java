package main.java.domain;

public class CreditAccount extends Account {
    private final double creditLimit;

    public CreditAccount(String accountNumber, Customer owner, double initialBalance, double creditLimit) {
        super(accountNumber, initialBalance, owner);
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public String getAccountType() {
        return "Кредитный (лимит: " + creditLimit + ")";
    }
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0){
            return false;
        }

        if (getBalance() + creditLimit < amount){
            return false;
        }
        setBalance(getBalance() - amount);
        return true;

    }
}


