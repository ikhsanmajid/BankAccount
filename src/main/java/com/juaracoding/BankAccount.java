package com.juaracoding;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    HashMap<Integer, AccountInfo> account;
    Integer accountNumber;

    public BankAccount(){
        account = new HashMap<Integer, AccountInfo>();
    }

    boolean selectAccount(Integer accountNumber){
        if (account.get(accountNumber) == null){
            return false;
        }else{
            this.accountNumber = accountNumber;
            return true;
        }
    }

    void setNewAccount(Integer accountNumber, String accountName, double accountBalance){
        account.put(accountNumber, new AccountInfo(accountName, accountBalance));
    }

    Map<String, Object> getAccountInformation(){
        Map<String, Object> result = new HashMap<>();
        AccountInfo value = account.get(accountNumber);
        result.put("accountNumber", accountNumber);
        result.put("accountName", value.getAccountName());
        result.put("accountBalance", value.getAccountBalance());

        return result;
    }

    Double deposit(double depositAmount){
        AccountInfo value = account.get(accountNumber);
        double balance = value.getAccountBalance();
        double addedAmount = balance + depositAmount;
        value.setAccountBalance(addedAmount);
        double newBalance = value.getAccountBalance();
        return newBalance;
    }

    String withdraw(double withdrawAmount){
        AccountInfo value = account.get(accountNumber);
        double balance = value.getAccountBalance();
        if (withdrawAmount > balance){
            return "Penarikan gagal, saldo tidak cukup";
        }else{
            double subtractedAmount = balance - withdrawAmount;
            value.setAccountBalance(subtractedAmount);
            return "Penarikan berhasil, saldo tersisa: "+value.getAccountBalance();
        }
    }

    String transferTo(Integer recipient, double amount){
        AccountInfo value = account.get(accountNumber);
        double balance = value.getAccountBalance();
        AccountInfo target = account.get(recipient);
//        System.out.println(target);
        if (target == null){
            return "Rekening penerima tidak terdaftar";
        } else if ( recipient == accountNumber ) {
            return "tidak bisa transfer ke diri sendiri";
        } else{
            if (amount > balance){
                return "Saldo tidak cukup";
            }else{
                double newBalance = balance - amount;
                value.setAccountBalance(newBalance);
                double targetBalance = target.getAccountBalance();
                double newTargetBalance = targetBalance + amount;
                target.setAccountBalance(newTargetBalance);
                return "Transfer dengan nominal: "+amount+" ke (no rekening:"+recipient+" nama rekening: "+target.getAccountName()+") berhasil";
            }
        }
    }

    String[] displayAccountInfo(){
        Map<String, Object> result = getAccountInformation();

        Object accountNumber = result.get("accountNumber");
        Object accountName = result.get("accountName");
        Object accountBalance = result.get("accountBalance");

        String data[] = {"No Account: "+accountNumber, "Nama Account: "+accountName, "Saldo Account: "+accountBalance};

        return data;
    }





    public static void main(String[] args) {
        BankAccount bank = new BankAccount();

        bank.setNewAccount(1, "Ikhsan", 20000);
        bank.setNewAccount(2, "Majid", 20000);
        bank.selectAccount(1);

        System.out.println(bank.transferTo(2, 20000));

        String[] data = bank.displayAccountInfo();

        for (String e: data){
            System.out.println(e);
        }





        //System.out.println(result);
        //System.out.println(bank.deposit(20000));
        //System.out.println(bank.withdraw(20000));

    }
}