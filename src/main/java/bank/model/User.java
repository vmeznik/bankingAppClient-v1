package bank.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String password;
    private String email;
    private int accNumber;
    private double balance;

    public User(String name, String password, String email, int accNumber, double balance) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public User(){
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", accNumber=" + accNumber +
                ", balance=" + balance +
                '}';
    }
}
