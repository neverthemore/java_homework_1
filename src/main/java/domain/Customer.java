package main.java.domain;
public class Customer{
    private final int id;
    private final String fullName;

    public Customer(int id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }

    public int getId(){
        return id;
    }
    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString(){
        return String.format("Клиент ID: %d, ФИО: %s]", id, fullName);
    }

}



