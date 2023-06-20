package com.db.misc.bankingapp.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountServiceImplTest {
    @Test
    public void testDepositMoney() {
        Account account1 = new Account("Deutche", "Bank", 112233);
        AccountService accountService = new AccountServiceImpl();
        accountService.depositMoney(account1, new BigDecimal(200));
        Assertions.assertEquals(account1.getBalance(), new BigDecimal(200));
    }

    @Test
    public void testWithDrawMoney() {
        Account account1 = new Account("Deutche", "Bank", 332211);
        AccountService accountService = new AccountServiceImpl();
        accountService.depositMoney(account1, new BigDecimal(200));
        accountService.withdrawMoney(account1, new BigDecimal(100));
        Assertions.assertEquals(account1.getBalance(), new BigDecimal(100));
    }
}
