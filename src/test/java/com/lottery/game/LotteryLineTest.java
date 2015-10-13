package com.lottery.game;

import com.lottery.game.LotteryLine;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by user on 11/10/2015.
 */
public class LotteryLineTest {

    private class LineTest{
        int num1;
        int num2;
        int num3;
    }

    @Test
    public void testCheckLineEqualsTen() throws Exception {
        ArrayList<LineTest> lineTests = new ArrayList<LineTest>(6);
        LineTest lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=1;
        lineTest.num3=0;
        lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=0;
        lineTest.num3=1;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=0;
        lineTest.num2=1;
        lineTest.num3=1;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=2;
        lineTest.num2=0;
        lineTest.num3=0;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=0;
        lineTest.num2=2;
        lineTest.num3=0;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=0;
        lineTest.num2=0;
        lineTest.num3=2;
        lineTests.add(lineTest);

        for (LineTest test : lineTests) {
            LotteryLine lotteryLine = new LotteryLine(test.num1,test.num2,test.num3);
            assertEquals(lotteryLine.checkLine(), 10);
        }
    }

    @Test
    public void testCheckLineAllSameEqualsFive() throws Exception {
        ArrayList<LineTest> lineTests = new ArrayList<LineTest>(6);
        LineTest lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=1;
        lineTest.num3=1;
        lineTest = new LineTest();
        lineTest.num1=0;
        lineTest.num2=0;
        lineTest.num3=0;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=2;
        lineTest.num2=2;
        lineTest.num3=2;
        lineTests.add(lineTest);

        for (LineTest test : lineTests) {
            LotteryLine lotteryLine = new LotteryLine(test.num1,test.num2,test.num3);
            assertEquals(lotteryLine.checkLine(), 5);
        }
    }

    @Test
    public void testCheckLineSecondThirdDiffersFromOneEqualsOne() throws Exception {
        ArrayList<LineTest> lineTests = new ArrayList<LineTest>(6);
        LineTest lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=2;
        lineTest.num3=2;
        lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=0;
        lineTest.num3=2;
        lineTest = new LineTest();
        lineTest.num1=0;
        lineTest.num2=1;
        lineTest.num3=2;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=2;
        lineTest.num2=1;
        lineTest.num3=1;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=2;
        lineTest.num2=0;
        lineTest.num3=1;
        lineTests.add(lineTest);

        for (LineTest test : lineTests) {
            LotteryLine lotteryLine = new LotteryLine(test.num1,test.num2,test.num3);
            assertEquals(lotteryLine.checkLine(), 1);
        }
    }

    @Test
    public void testCheckLineEqualsZero() throws Exception {
        ArrayList<LineTest> lineTests = new ArrayList<LineTest>(6);
        LineTest lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=1;
        lineTest.num3=2;
        lineTest = new LineTest();
        lineTest.num1=1;
        lineTest.num2=2;
        lineTest.num3=1;
        lineTest = new LineTest();
        lineTest.num1=0;
        lineTest.num2=1;
        lineTest.num3=0;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=2;
        lineTest.num2=1;
        lineTest.num3=2;
        lineTests.add(lineTest);
        lineTest = new LineTest();
        lineTest.num1=2;
        lineTest.num2=2;
        lineTest.num3=1;
        lineTests.add(lineTest);

        for (LineTest test : lineTests) {
            LotteryLine lotteryLine = new LotteryLine(test.num1,test.num2,test.num3);
            assertEquals(lotteryLine.checkLine(), 0);
        }
    }
}