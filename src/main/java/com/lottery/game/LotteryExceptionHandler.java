package com.lottery.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.OptimisticLockException;

/**
 * Created by user on 12/10/2015.
 */
@ControllerAdvice
public class LotteryExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Lottery Ticket Not Found")
    @ExceptionHandler(TicketNotFoundException.class)
    public void ticketNotFound(){}

    @ResponseStatus(value = HttpStatus.NOT_MODIFIED,reason = "Lottery Ticket Already Checked")
    @ExceptionHandler(TicketCheckedException.class)
    public void ticketAlreadyChecked(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public void invalidRequest(){};

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Modified by another User")
    @ExceptionHandler(OptimisticLockException.class)
    public void concurrentAccess(){};
}
