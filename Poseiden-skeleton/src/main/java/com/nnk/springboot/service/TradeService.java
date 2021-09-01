package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	public void validateTrade(Trade trade) throws NegativeNumberException {
		
        if (trade.getBuyQuantity() < 0){
            throw new NegativeNumberException("Buy Quantity cannot be negative");
        }

        Trade addTrade = new Trade();
        
        addTrade.setAccount(trade.getAccount());
        
        addTrade.setType(trade.getType());
        
        addTrade.setBuyQuantity(trade.getBuyQuantity());
        
        addTrade.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        
        tradeRepository.save(addTrade);
        
    }

    public void updateTrade(Integer id, Trade trade) throws NegativeNumberException {
    	
        if (trade.getBuyQuantity() < 0){
            throw new NegativeNumberException("Buy Quantity cannot be negative");
        }
        
        Trade tradeInDb = tradeRepository.findTradeById(id);
        
        tradeInDb.setAccount(trade.getAccount());
        
        tradeInDb.setType(trade.getType());
        
        tradeInDb.setBuyQuantity(trade.getBuyQuantity());
        
        tradeRepository.save(tradeInDb);
        
    }

    public void deleteTrade(Integer id) {
    	
        tradeRepository.deleteById(id);
        
    }
	

}
