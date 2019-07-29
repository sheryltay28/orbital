package com.orbital.saveme.model;

public class Budget {
    public double spendingBudget;
    public double savingsBudget;

    public Budget(double spendingBudget, double savingsBudget) {
        this.spendingBudget = spendingBudget;
        this.savingsBudget = savingsBudget;
    }

    public Budget() {}

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
