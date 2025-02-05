package com.orbital.saveme.model;

import java.util.Date;

public class Transaction {

    private String transactionType;
    private String transactionName;
    private double amount;
    private Date date;

    public Transaction() {
    }

    public Transaction(String transactionType, String transactionName, double amount, Date date) {
        this.transactionType = transactionType;
        this.transactionName = transactionName;
        this.amount = amount;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
