package com.orbital.saveme.model;

public class Transaction {

    private String transactionType;
    private String transactionName;
    private double amount;

    public Transaction() {
    }

    public Transaction(String transactionType, String transactionName, double amount) {
        this.transactionType = transactionType;
        this.transactionName = transactionName;
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
