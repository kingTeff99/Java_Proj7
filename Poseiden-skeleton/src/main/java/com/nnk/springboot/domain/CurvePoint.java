package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.sql.Timestamp;

@Data@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "CurvePoint")
public class CurvePoint {
	
	public CurvePoint(Integer curveId, double term, double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value; 
	}
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	
	@NotNull(message = "must not be null")
	private Integer curveId ;
	
	private Timestamp asOfDate ;
	
	private Double term ;
	
	@Positive(message = "must not be negative")
	private Double value ;
	
	private Timestamp creationDate ;
	
}
