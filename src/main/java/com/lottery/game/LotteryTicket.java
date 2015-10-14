package com.lottery.game;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by user on 11/10/2015.
 */
@Entity
public class LotteryTicket {

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private List<LotteryLine> lines;

    private boolean isChecked = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@Version
	private long version = 0L;

    public LotteryTicket(){}

    public LotteryTicket(long id) {
        this.id = id;
    }

    public LotteryTicket(List<LotteryLine> lines) {
        this.lines = lines;
    }

    /**
     * Used to add a line to the lottery ticket
     * @param line
     */
    public void addLine(LotteryLine line){
        if(lines == null){
            this.lines = new ArrayList<LotteryLine>();
        }
        this.lines.add(line);
    }

    /**
     * Gets the ID of the ticket
     * @return
     */
    public long getTicketId() {
        return id;
    }

    /**
     * Gets the lines associated with the ticket
     * @return
     */
    public List<LotteryLine> getLines() {
        return lines;
    }

    /**
     * Marks the ticket as checked
     */
    public void checkTicket(){
        isChecked = true;
    }

    /**
     * Test to see if the ticket is checked
     * @return
     */
    public boolean isChecked() {
        return isChecked;
    }
}
