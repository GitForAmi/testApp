package com.db.misc.bankingapp.account;

import java.math.BigDecimal;

public interface AccountService {

    void depositMoney(Account account, BigDecimal depositAmount);

    void withdrawMoney(Account account, BigDecimal withdrawalAmount);

}
