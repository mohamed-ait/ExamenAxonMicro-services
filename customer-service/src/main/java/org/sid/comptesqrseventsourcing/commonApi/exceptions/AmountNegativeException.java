package org.sid.comptesqrseventsourcing.commonApi.exceptions;

public class AmountNegativeException extends RuntimeException{
    public AmountNegativeException(String message) {
        super(message);
    }
}
