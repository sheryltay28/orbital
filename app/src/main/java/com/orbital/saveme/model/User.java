package com.orbital.saveme.model;

public class User {
    public String userName;
    public String userNumber;
    public double expenditure;
    public double income;
    public double spendingBudget;
    public double savingsBudget;

    public User(String userName, String userNumber) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.expenditure = 0;
        this.income = 0;
        this.spendingBudget = 0;
        this.savingsBudget = 0;
    }

    public User() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getSpendingBudget() {
        return spendingBudget;
    }

    public void setSpendingBudget(double spendingBudget) {
        this.spendingBudget = spendingBudget;
    }

    public double getSavingsBudget() {
        return savingsBudget;
    }

    public void setSavingsBudget(double savingsBudget) {
        this.savingsBudget = savingsBudget;
    }
}
