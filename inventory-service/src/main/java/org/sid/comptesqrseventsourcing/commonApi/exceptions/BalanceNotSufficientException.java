package org.sid.comptesqrseventsourcing.commonApi.exceptions;

public class BalanceNotSufficientException extends RuntimeException{
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
