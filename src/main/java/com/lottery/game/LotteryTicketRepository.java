package com.lottery.game;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by user on 13/10/2015.
 * Simple In memory Database Responsitory for holding the data.
 * Could be extended to provide different implementations.
 **/
public interface LotteryTicketRepository extends CrudRepository<LotteryTicket,Long>  {

}
