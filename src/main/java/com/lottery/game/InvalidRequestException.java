package com.lottery.game;

/**
 * Created by user on 12/10/2015.
 */
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
