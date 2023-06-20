package com.db.misc.app;

import com.db.misc.bankingapp.account.AccountServiceImpl;
import com.db.misc.bankingapp.bank.Bank;
import com.db.misc.bankingapp.bank.BankImpl;

import java.util.Scanner;

/**
 * This is the main class which initiates the banking app and allows user to create account, deposit/withdraw money.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new BankImpl(new AccountServiceImpl());
        while(true) {
            // show options to chose from.
            printMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 5, try again.");
                continue;
            }
            switch (choice) {
                case 1 -> bank.registerAccount();
                case 2 -> bank.depositMoney();
                case 3 -> bank.withdrawMoney();
                case 4 -> bank.showBalance();
                case 5 -> {
                    System.exit(0);
                    return;
                }
                default -> System.out.println("Wrong input");
            }
        }
    }
    private static void printMenu() {
        System.out.println("""
                Hello, press:\s
                \r1.Register
                \r2.Deposit
                \r3.Withdraw
                \r4.Balance
                \r5.Quit""");
    }
}