package com.orbital.saveme.model;

public class Expenditure {
    private String expenditureType;
    private double amount;

    public Expenditure(String expenditureType, double amount) {
        this.expenditureType = expenditureType;
        this.amount = amount;
    }

    public String getExpenditureType() {
        return this.expenditureType;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setExpenditureType(String expenditureType) {
        this.expenditureType = expenditureType;
    }



    public void setAmount(double amount) {
        this.amount = amount;
    }
}
