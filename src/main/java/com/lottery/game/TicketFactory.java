package com.lottery.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/10/2015.
 */
@Component
public class TicketFactory implements TicketFactoryInterface {

    @Autowired
    LineGeneratorStraegy lineGeneratorStraegy;


    /**
     * Simple Factory to generate Tickets
     * @param numLines
     * @return
     */
    public LotteryTicket generateTicket(int numLines){
        List<LotteryLine> lines = new ArrayList<LotteryLine>(numLines);
        for(int i=0;i<numLines;i++){
            lines.add(lineGeneratorStraegy.generateLine());
        }
        return new LotteryTicket(lines);
    }


}
