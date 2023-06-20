package com.db.misc.bankingapp.account;

import java.math.BigDecimal;

public class Account {
    private final String firstName;
    private final String lastName;
    private BigDecimal balance;

    private final long accountNumber;

    public Account(String firstName, String lastName, long accountNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(0);
    }

    public BigDecimal getBalance(){
        return this.balance;
    }
    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance;
    }

    public long getAccountNumber(){
        return this.accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", phoneNumber='" + accountNumber + '\'' +
                '}';
    }
}
