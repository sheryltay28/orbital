package com.orbital.saveme.model;

public class Income {
    private String incomeType;
    private double amount;

    public Income(String incomeType, double amount) {
        this.incomeType = incomeType;
        this.amount = amount;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
