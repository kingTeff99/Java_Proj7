package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {
	
	@Autowired
	private CurvePointRepository curvePointRepository;
	
	public void validateCurvePoint(CurvePoint curvePoint) throws NegativeNumberException {

        if(curvePoint.getCurveId() < 0 || curvePoint.getTerm() < 0 || curvePoint.getValue() < 0){
            throw new NegativeNumberException("Curve Point cannot be negative");
        }
        CurvePoint addCurvePoint = new CurvePoint();
        
        addCurvePoint.setCurveId(curvePoint.getCurveId());
        
        addCurvePoint.setTerm(curvePoint.getTerm());
        
        addCurvePoint.setValue(curvePoint.getValue());
        
        addCurvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        
        curvePointRepository.save(addCurvePoint);
        
    }

    public void updateCurvePoint(Integer id, CurvePoint curvePoint) throws NegativeNumberException {
    	
        if(curvePoint.getCurveId() < 0 || curvePoint.getTerm() < 0 || curvePoint.getValue() < 0){
            throw new NegativeNumberException("Curve Point cannot be negative");
        }
        
        CurvePoint curvePointInDb = curvePointRepository.findCurvePointById(id);
        
        curvePointInDb.setCurveId(curvePoint.getCurveId());
        
        curvePointInDb.setTerm(curvePoint.getTerm());
        
        curvePointInDb.setValue(curvePoint.getValue());
        
        curvePointRepository.save(curvePointInDb);
        
    }

    public void deleteCurvePoint(Integer id) {
    	
        curvePointRepository.deleteById(id);
        
    }

}
