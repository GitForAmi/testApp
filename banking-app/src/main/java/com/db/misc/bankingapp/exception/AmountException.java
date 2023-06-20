package com.db.misc.bankingapp.exception;

import java.util.InputMismatchException;

public class AmountException extends InputMismatchException {
    public AmountException(String message) {
        super(message);
    }
}
