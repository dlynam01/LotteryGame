package com.lottery.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 11/10/2015.
 */
@RestController
public class LotteryWebService implements LotteryWebInterface {

    @Autowired
    TicketFactoryInterface ticketFactory;

    @Autowired
    LotteryTicketRepository lotteryTicketRepository;

    @Autowired
    LineGeneratorStraegy lineGeneratorStraegy;

    /**
     * {@inheritDoc}
     * @param numberLines
     * @return
     */
    @Override
    public LotteryTicket createLotteryTicket(Integer numberLines) {
        if(numberLines == null || numberLines <= 0) throw new InvalidRequestException("Invalid Number of Lines");
        LotteryTicket lotteryTicket = ticketFactory.generateTicket(numberLines);
        lotteryTicketRepository.save(lotteryTicket);
        return lotteryTicket;
    }


    /**
     * {@inheritDoc}
     * @param numberLines
     * @param ticketId
     * @return
     */
    @Override
    public LotteryTicket addLineToTicket(Integer numberLines, Long ticketId) {
        if(ticketId == null || ticketId <= 0) throw new InvalidRequestException("Invalid TicketId");
        if(numberLines == null || numberLines <= 0) throw new InvalidRequestException("Invalid Number of Lines");
        LotteryTicket lotteryTicket = lotteryTicketRepository.findOne(ticketId);
        if(lotteryTicket==null)throw new TicketNotFoundException();
        if(!lotteryTicket.isChecked()){
            for(int i=0;i<numberLines;i++){
                lotteryTicket.addLine(lineGeneratorStraegy.generateLine());
            }
        }else{
            throw new TicketCheckedException();
        }
        lotteryTicketRepository.save(lotteryTicket);
        return lotteryTicket;
    }

    /**
     * {@inheritDoc}
     * @param ticketId
     * @return
     */
    @Override
    public TicketResult checkTicket(Long ticketId) {
        if(ticketId == null || ticketId <= 0) throw new InvalidRequestException("Invalid TicketId");
        LotteryTicket lotteryTicket = lotteryTicketRepository.findOne(ticketId);
        if(lotteryTicket==null)throw new TicketNotFoundException();
        lotteryTicket.checkTicket();
        lotteryTicketRepository.save(lotteryTicket);
        TicketResult ticketResult = new TicketResult(lotteryTicket);
        return ticketResult;
    }
}
