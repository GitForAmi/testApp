package com.db.misc.bankingapp.account;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {


    public void depositMoney(Account account, BigDecimal depositAmount){
        account.setBalance(account.getBalance().add(depositAmount));
        System.out.println("You have deposited " +depositAmount + " to your account." + "\n" +
                "Balance is now: " + account.getBalance());
    }

    public void withdrawMoney(Account account, BigDecimal withdrawalAmount){
        if(account.getBalance().compareTo(withdrawalAmount) < 0) {
            System.out.println("You don't have enough funds.");
        } else {
            account.setBalance(account.getBalance().subtract(withdrawalAmount));
            System.out.println("You have withdrawal " + withdrawalAmount + " from your account." + "\n" +
                    "Balance is now: " + account.getBalance());
        }
    }

}
