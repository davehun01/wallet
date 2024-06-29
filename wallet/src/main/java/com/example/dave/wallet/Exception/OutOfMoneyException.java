package com.example.dave.wallet.Exception;

public class OutOfMoneyException extends Exception{

    public OutOfMoneyException( ) {
        super();
    }
    public OutOfMoneyException(String errorMessage) {
        System.out.println(errorMessage);
    }
}
