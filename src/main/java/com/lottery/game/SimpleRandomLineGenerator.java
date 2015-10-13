package com.lottery.game;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by user on 11/10/2015.
 */
@Component
public class SimpleRandomLineGenerator implements LineGeneratorStraegy {
    private Random random = new Random(System.currentTimeMillis());

   private int generateLotteryNumber(){
       return random.nextInt(Integer.MAX_VALUE) % 3;
   }

    /**
     * Simple Strategy To Generate Random Lines
     * @return
     */
    @Override
    public LotteryLine generateLine() {
        return new LotteryLine(generateLotteryNumber(),generateLotteryNumber(),generateLotteryNumber());
    }
}
