package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
	
	Trade findTradeById(Integer id);
	
	Trade findIdByAccountAndTypeAndBuyQuantity(String account, String type, Double buyQuantity );
}
