package com.lottery.game;

import com.sun.javafx.collections.ArrayListenerHelper;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Created by user on 11/10/2015.
 */
public class TicketResult {

    private List<LineResult> results = new ArrayList<LineResult>();
    private LotteryTicket ticket;

    public TicketResult(LotteryTicket ticket) {
        for (LotteryLine lotteryLine : ticket.getLines()) {
            results.add(new LineResult(lotteryLine));
        }
        this.ticket = ticket;

    }

    /**
     * Returns the Sorted by Highest Value First List of Line Results.
     * @return
     */
    public List<LineResult> getResults() {
        return results.stream().sorted((o1, o2) -> LineResult.compare(o2, o1)).collect(toList());
    }
}
