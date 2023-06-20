package com.db.misc.bankingapp.bank;

import com.db.misc.bankingapp.account.Account;
import com.db.misc.bankingapp.account.AccountService;
import com.db.misc.bankingapp.account.AccountServiceImpl;
import com.db.misc.bankingapp.exception.AmountException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BankImpl implements Bank {
    private final List<Account> bankAccounts;
    private final Scanner scanner;
    private final AtomicLong last_time_ms;
    private final AccountService accountService;
    public BankImpl(AccountServiceImpl accountService) {
        bankAccounts = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.accountService = accountService;
        this.last_time_ms = new AtomicLong();
    }

    /**
     * registers/creates a new account with the first/last name provided on console.
     */
    @Override
    public void registerAccount() {
        System.out.println("First name?");
        String firstName = scanner.next();
        System.out.println("Last name?");
        String lastName = scanner.next();
        long accountNumber = generateAccountNumber();
        bankAccounts.add(new Account(firstName, lastName, accountNumber));
        System.out.println("You have created account successfully!" + "\n" +
                    "Your accountNumber is: " + accountNumber);
    }

    /**
     * generates new unique account number based on current timestamp in milliseconds, used AtomicLong for thread safety
     * @return newly generate Account Number
     */
    private long generateAccountNumber() {
        long now = System.currentTimeMillis();
        while(true) {
            long lastTime = this.last_time_ms.get();
            if (lastTime >= now)
                now = lastTime+1;
            if (this.last_time_ms.compareAndSet(lastTime, now))
                return now;
        }
    }

    /**
     * adds money/amount to the existing account and updates the balance
     */
    @Override
    public void depositMoney() {
        long accountNumber = readAccFromConsole();
        if (validAccount(accountNumber)) {
            BigDecimal amount = readAmountFromConsole();
            if(amount != null) {
                List<Account> selectedAccountList = findAccount(accountNumber);
                if (selectedAccountList != null && !selectedAccountList.isEmpty()) {
                    Account selectedAcc = selectedAccountList.get(0);
                    accountService.depositMoney(selectedAcc, amount);
                }
            }
        }
    }

    /**
     * withdraws money from the account and updates the balance on the account.
     */
    @Override
    public void withdrawMoney() {
        long accountNumber = readAccFromConsole();
        if (validAccount(accountNumber)) {
            BigDecimal amount = readAmountFromConsole();
            if(amount != null) {
                List<Account> selectedAccountList = findAccount(accountNumber);
                if (selectedAccountList != null && !selectedAccountList.isEmpty()) {
                    Account selectedAcc = selectedAccountList.get(0);
                    accountService.withdrawMoney(selectedAcc, amount);
                }
            }
        }
    }

    /**
     * checks if the account exists and displays the balance on console
     */
    @Override
    public void showBalance() {
        long accountNumber = readAccFromConsole();
        if (validAccount(accountNumber)) {
            List<Account> selectedAccountList = findAccount(accountNumber);
            if (selectedAccountList != null && !selectedAccountList.isEmpty()) {
                Account selectedAcc = selectedAccountList.get(0);
                System.out.println("Balance on the account " + selectedAcc.getAccountNumber() + " is: " + selectedAcc.getBalance());
            }
        }
    }

    private long readAccFromConsole() {
        System.out.println("Please enter your Account Number:");
        long accountNumber = 0;
        try {
            accountNumber = Long.parseLong(scanner.next());
        } catch (Exception e) {
            // do nothing, exception taken care later.
        }
        return accountNumber;
    }

    private BigDecimal readAmountFromConsole() {
        System.out.println("Please enter the amount:");
        BigDecimal amount = null;
        try {
            amount = new BigDecimal(scanner.next());
            if (amount.compareTo(new BigDecimal(0)) <= 0 ) {
                amount = null;
                throw new AmountException("Amount cannot be negative");
            }
        } catch (RuntimeException e) {
            System.out.println("Amount entered is not correct format, please try again");
        }
        return amount;
    }
    private boolean ifAccountExists(long accountNumber) {
        if (accountNumber > 0) {
            List<Account> foundList = findAccount(accountNumber);
            return foundList != null && !foundList.isEmpty();
        }
        return false;
    }

    private List<Account> findAccount(long accountNumber) {
        return accountNumber > 0 ? bankAccounts.stream().filter(account -> account.getAccountNumber() == accountNumber).collect(Collectors.toList())
                : null;
    }
    private boolean validAccount(long accountNumber){
        if(accountNumber > 0 && ifAccountExists(accountNumber)){
            System.out.println("Account number exists " + accountNumber);
            return true;
        } else {
            System.out.println("Account number is not valid, please enter correct account number");
            return false;
        }
    }
}
