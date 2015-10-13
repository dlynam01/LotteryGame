package com.lottery.game;

import org.springframework.web.bind.annotation.*;

/**
 * Created by user on 11/10/2015.
 */
@RestController
@RequestMapping("/lottery")
public interface LotteryWebInterface {


    /**
     * Create Lottery Ticket API
     * This API supports HTTP POST Method Only.
     * It is used to create a Lottery Ticket.
     * This API must contain numberLines as an indication of the number of lines to add to the ticket.
     * Example:
     * http://localhost:8080/lottery/ticket/create?numberLines=1
     *
     * Response:
     * {"lines":[{"lineNumbers":[1,0,1]}],"checked":false,"ticketId":1}
     * @param numberLines
     * @return
     */
    @RequestMapping(value = "/ticket/create",method = RequestMethod.POST)
    @ResponseBody LotteryTicket createLotteryTicket(@RequestParam(value="numberLines") Integer numberLines);

    /**
     * Update Lottery Ticket API
     * This API supports HTTP PUT Method Only
     * It is used to append a number of Lines to a Lottery Ticket
     * This API must contain numberLines as an indication of the number of lines to add to the ticket.
     * This API must also contain the ticketId of which to apply the new lines too.
     * Example:
     * http://localhost:8080/lottery/ticket/update?numberLines=2&ticketId=1
     *
     * Response:
     * {"lines":[{"lineNumbers":[1,0,1]},{"lineNumbers":[2,0,1]},{"lineNumbers":[2,1,1]}],"checked":false,"ticketId":1}
     *
     * Errors:
     * HTTP 304 Not Modified - Thrown if the Lottery Ticket Cannot be modified due to already been checked.
     * HTTP 404 Not Found - Thrown if the Lottery Ticket was not found, Check the ID is correct.
     * HTTP 400 Bad Request - Thrown if there is missing or incorrect data supplied to the API
     * @param numberLines
     * @param ticketId
     * @return
     */
    @RequestMapping(value = "/ticket/update", method = RequestMethod.PUT)
    @ResponseBody LotteryTicket addLineToTicket(@RequestParam(value="numberLines") Integer numberLines,@RequestParam(value="ticketId") Long ticketId);

    /**
     * Check Lottery Ticket API
     * This API supports HTTP GET Method Only
     * It is used to mark and return the values of a Lottery Ticket
     * Note: Lottery Tickets cannot be amended after they are checked.
     * This API must contain the ticketId of which to check.
     *
     * Example:
     * http://localhost:8080/lottery/ticket/check?ticketId=1
     *
     * Response:
     * {"results":[{"lotteryLine":{"lineNumbers":[1,0,1]},"lotteryLineValue":10},{"lotteryLine":{"lineNumbers":[2,0,1]},"lotteryLineValue":1},{"lotteryLine":{"lineNumbers":[2,1,1]},"lotteryLineValue":1}]}
     *
     * Errors:
     * HTTP 404 Not Found - Thrown if the Lottery Ticket was not found, Check the ID is correct.
     * HTTP 400 Bad Request - Thrown if there is missing or incorrect data supplied to the API
     * @param ticketId
     * @return
     */
    @RequestMapping(value = "/ticket/check", method = RequestMethod.GET)
    @ResponseBody TicketResult checkTicket(@RequestParam(value="ticketId") Long ticketId);

}
