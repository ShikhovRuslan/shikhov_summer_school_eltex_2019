package ru.eltex.app.java.lab7;

public class DelException extends Exception {

    private int errorCode;

    public DelException(int errorCode) {
        super(ErrorMessages.values()[errorCode - 1].toString());
        this.errorCode = errorCode;
    }

    int getErrorCode() {
        return errorCode;
    }

}