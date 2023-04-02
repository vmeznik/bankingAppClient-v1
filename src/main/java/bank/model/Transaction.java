package bank.model;

import bank.service.BankingService;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int id;
    private int senderAccNumber;
    private int receiverAccNumber;
    private double amount;

    public Transaction(int senderAccNumber, int receiverAccNumber, double amount) {
        this.id = -1;
        this.senderAccNumber = senderAccNumber;
        this.receiverAccNumber = receiverAccNumber;
        this.amount = amount;
    }

    public Transaction() {

    }

    public int getId() {
        return id;
    }

    public int getSenderAccNumber() {
        return senderAccNumber;
    }

    public int getReceiverAccNumber() {
        return receiverAccNumber;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        if (BankingService.getMyAccNumber() == senderAccNumber) {
            return "-" + amount + "    To: " + receiverAccNumber;
        }
        return "+" + amount + "  From: " + senderAccNumber;
    }
}
