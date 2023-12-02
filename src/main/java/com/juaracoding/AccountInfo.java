package com.juaracoding;

public class AccountInfo {
    private String accountName;
    private double accountBalance;



    public AccountInfo(String accountName, double accountBalance){
        setAccountName(accountName);
        setAccountBalance(accountBalance);
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
