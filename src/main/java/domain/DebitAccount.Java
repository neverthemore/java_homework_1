package main.java.domain;

public class DebitAccount extends Account{
    public DebitAccount(String accountNumber, Customer owner, double initialBalance) {
        super(accountNumber, initialBalance, owner);
    }

    @Override
    public String getAccountType() {
        return"Дебетовый";
    }
}