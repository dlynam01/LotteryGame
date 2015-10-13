package com.lottery.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by user on 12/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LotteryWebServiceTest {

    @Mock
    private LotteryTicketRepository lotteryRepository;

    @Mock
    private TicketFactoryInterface ticketFactory;

    @Mock
    private LineGeneratorStraegy lineGeneratorStraegy;

    @InjectMocks
    LotteryWebService lotteryWebService;



    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    public void setup(){
        Random random = new Random(System.currentTimeMillis());
        ArrayList<LotteryLine> lotteryLines = new ArrayList<LotteryLine>(1);
        lotteryLines.add(new LotteryLine(1,1,1));
        LotteryTicket lotteryTicket1 = new LotteryTicket(lotteryLines);
        LotteryTicket lotteryTicketDb = new LotteryTicket(1);
        lotteryTicket1.getLines().stream().forEach(l ->lotteryTicketDb.addLine(l));
        when(ticketFactory.generateTicket(anyInt())).thenReturn(lotteryTicket1);
        when(lineGeneratorStraegy.generateLine()).thenReturn(new LotteryLine(random.nextInt(Integer.MAX_VALUE)%3,random.nextInt(Integer.MAX_VALUE)%3,random.nextInt(Integer.MAX_VALUE)%3));
        when(lotteryRepository.findOne(1L)).thenReturn(lotteryTicketDb);
    }

    @Test
    public void testCreateLotteryTicket() throws Exception {
        setup();
        LotteryTicket lotteryTicket = lotteryWebService.createLotteryTicket(1);
        assertNotNull(lotteryTicket);
        assertNotNull(lotteryTicket.getLines());
        assertTrue(lotteryTicket.getLines().size()==1);
    }

    @Test
    public void testAddLineToTicket() throws Exception {
        setup();
        LotteryTicket lotteryTicket = lotteryWebService.createLotteryTicket(1);
        assertNotNull(lotteryTicket);
        assertNotNull(lotteryTicket.getLines());
        assertTrue(lotteryTicket.getLines().size()==1);
        LotteryTicket amendedTicket = lotteryWebService.addLineToTicket(5, 1L);
        assertNotNull(amendedTicket);
        assertNotNull(amendedTicket.getLines());
        assertTrue(amendedTicket.getLines().size()==6);
    }

    @Test
    public void testCheckTicket() throws Exception {
        setup();
        LotteryTicket lotteryTicket = lotteryWebService.createLotteryTicket(2);
        assertNotNull(lotteryTicket);
        assertNotNull(lotteryTicket.getLines());
        assertTrue(lotteryTicket.getLines().size()==1);
        TicketResult ticketResult = lotteryWebService.checkTicket(1L);
        assertNotNull(ticketResult);
        assertNotNull(ticketResult.getResults());
        assertTrue(ticketResult.getResults().size()==1);
        assertTrue(ticketResult.getResults().get(0).getLotteryLineValue()==5);
    }


    @Test(expected = InvalidRequestException.class)
    public void addLineExpectInvalidTicketIdNegNum(){
        setup();
        lotteryWebService.checkTicket(-1L);
    }

    @Test(expected = InvalidRequestException.class)
    public void addLineExpectInvalidTicketIdZero(){
        setup();
        lotteryWebService.checkTicket(0L);
    }

    @Test(expected = InvalidRequestException.class)
    public void checkTicketExpectInvalidTicketIdNegNum(){
        setup();
        lotteryWebService.checkTicket(-1L);
    }

    @Test(expected = InvalidRequestException.class)
    public void checkTicketExpectInvalidTicketIdZero(){
        setup();
        lotteryWebService.checkTicket(0L);
    }

    @Test(expected = TicketNotFoundException.class)
    public void addLineExpectExpectTicketNotFound(){
        setup();
        lotteryWebService.addLineToTicket(1, 10L);
    }

    @Test(expected = TicketNotFoundException.class)
    public void checkTicketExpectTicketNotFound(){
        setup();
        lotteryWebService.checkTicket(5L);
    }
}