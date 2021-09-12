package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	
	private Integer curveId ;
	
	private Timestamp asOfDate ;
	
	private Double term ;
	
	private Double value ;
	
	private Timestamp creationDate ;
	
}
