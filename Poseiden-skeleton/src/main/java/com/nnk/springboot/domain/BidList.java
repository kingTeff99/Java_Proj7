package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Data@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {
	
	public BidList(String account, String type, Double bidQuantity) {
		
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer bidListId ;
	
	private String account ;
	
	private String type ;
	
	private Double bidQuantity ;
	
	private Double askQuantity ;
	
	private Double bid ;
	
	private Double ask ;
	
	private String benchmark ;
	
	private Timestamp bidListDate ;
	
	private String commentary ;
	
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
