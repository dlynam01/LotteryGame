package com.lottery.game;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by user on 11/10/2015.
 */
@Entity
public class LotteryLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lineId;

    private int[] lineNumbers = new int[3];

    public LotteryLine(){}

    public LotteryLine(int num1,int num2,int num3){
        lineNumbers[0] = num1;
        lineNumbers[1] = num2;
        lineNumbers[2] = num3;
    }

    /**
     * Returns the array of numbers in the line
     * @return
     */
    public int[] getLineNumbers() {
        return lineNumbers;
    }

    /**
     * Returns the value of the give line as per the spec
     * @return
     */
    public int checkLine(){
        int result = 0;
        if(lineNumbers[0]+lineNumbers[1]+lineNumbers[2]==2){
            result = 10;
        }else if(lineNumbers[0]==lineNumbers[1] && lineNumbers[1]==lineNumbers[2]){
            result = 5;
        }else if(lineNumbers[0]!=lineNumbers[1] && lineNumbers[0] != lineNumbers[2]){
            result = 1;
        }
        return result;
    }
}
