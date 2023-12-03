package com.juaracoding;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class BankAccountTest {
    BankAccount account;

    @BeforeMethod
    void setUp(){
        account = new BankAccount();
        account.setNewAccount(1, "Ikhsan", 20000);
        account.setNewAccount(2, "Majid", 20000);
        account.selectAccount(1);
    }

    @Test()
    public void testSelectAccount(){
        boolean actual = account.selectAccount(2);
        Assert.assertTrue(actual);
    }

    @Test(dependsOnMethods = "testSelectAccount")
    public void testAccountInformation(){
        Map<String, Object> result = account.getAccountInformation();
        Object accountName = result.get("accountName");
        Object accountBalance = result.get("accountBalance");

        String expectedAccountName = "Ikhsan";
        double expectedAccountBalance = 20000;

        Assert.assertEquals(accountName, expectedAccountName, "accountName berbeda");
        Assert.assertEquals(accountBalance, expectedAccountBalance, "accountBalance berbeda");
    }

    @Test(dependsOnMethods = "testAccountInformation")
    public void testDeposit(){
        Double deposit = account.deposit(20000);
        double expectedBalance = 40000;
        Assert.assertEquals(deposit, expectedBalance, "Deposit gagal");
    }

    @Test(dependsOnMethods = "testAccountInformation")
    public void testWithdraw(){
        String withdraw = account.withdraw(20000);
        Assert.assertTrue(withdraw.contains("berhasil"), "Withdraw gagal");
    }

    @Test(dependsOnMethods = "testAccountInformation")
    public void testTransferTo(){
        String transfer = account.transferTo(2, 20000);
        Assert.assertTrue(transfer.contains("berhasil"), "Transfer gagal");
    }

}
