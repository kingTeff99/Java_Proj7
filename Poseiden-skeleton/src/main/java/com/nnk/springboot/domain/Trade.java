package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "Trade")
public class Trade {
	
	public Trade(String account, String type) {
		this.account = account;
		this.type = type;
	}
	
	public Trade(String account, String type, Double buyQuantity ) {
        this.type = type;
        this.account = account;
        this.buyQuantity = buyQuantity;
    }

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	
	private String account ;
	
	private String type ;
	
	private Double buyQuantity ;
	
	private Double sellQuantity ;
	
	private Double buyPrice ;
	
	private Double sellPrice ;
	
	private String benchmark ;
	
	private Timestamp tradeDate ;
	
	private String security ;
	
	private String status ;
	
	private String trader ;
	
	private String book ;
	
	private String creationName ;
	
	private Timestamp creationDate ;
	
	private String revisionName ;
	
	private Timestamp revisionDate ;
	
	private String dealName ;
	
	private String dealType ;
	
	private String sourceListId ;
	
	private String side ;
	
}
