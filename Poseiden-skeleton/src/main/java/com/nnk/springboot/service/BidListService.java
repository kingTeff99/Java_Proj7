package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
	
	@Autowired
	private BidListRepository bidListRepository;
	
	public void validate(BidList bidList) throws NegativeNumberException{
		
		if(bidList.getBidQuantity() < 0.0) 
			throw new NegativeNumberException("Bid Quantity cannot be negative");
		
		BidList bid = new BidList();
		
		bid.setAccount(bidList.getAccount());
		
        bid.setType(bidList.getType());
        
        bid.setBidQuantity(bidList.getBidQuantity());
        
        bid.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        
        bidListRepository.save(bid);
		
	}
	
	public void updateBid(Integer id, BidList bidList) throws NegativeNumberException {
		
	   if(bidList.getBidQuantity() < 0.0)
	            throw new NegativeNumberException("Bid Quantity cannot be negative");

	   BidList bidListInDb = bidListRepository.findBidListById(id);

	   bidListInDb.setAccount(bidList.getAccount());
	        
	   bidListInDb.setType(bidList.getType());
	        
	   bidListInDb.setBidQuantity(bidList.getBidQuantity());
	        
	   bidListRepository.save(bidListInDb);
	}
	
	public void deleteBid(Integer id) {
		
        bidListRepository.deleteById(id);
        
    }

}
