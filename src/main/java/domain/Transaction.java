package main.java.domain;

import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final String fromAccountNumber;
    private final String toAccountNumber;
    private final LocalDateTime timeStamp;
    private final boolean success;
    private final String message;

    public Transaction(TransactionType type,  double amount, String fromAccountNumber, String toAccountNumber, LocalDateTime timeStamp, boolean success, String message) {
        this.type = type;
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.timeStamp = timeStamp;
        this.success = success;
        this.message = message;
    }

    public TransactionType getType() {return type;}
    public double getAmount() {return amount;}
    public String getFromAccountNumber() {return fromAccountNumber;}
    public String getToAccountNumber() {return toAccountNumber;}
    public LocalDateTime getTimeStamp() {return timeStamp;}
    public boolean isSuccess() {return success;}
    public String getMessage() {return message;}

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f | Счет отправителя: %s | Счет получателя: %s | Статус: %s | Сообщение: %s",
                timeStamp, type, amount,
                fromAccountNumber != null ? fromAccountNumber : "N/A",
                toAccountNumber != null ? toAccountNumber : "N/A",
                success ? "Успешно" : "Ошибка",
                message);
    }
}