package com.peru.combi.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.interfaces.PruebaGpsService;
import com.peru.combi.repository.PruebaGCbRepository;

public class PruebaGpsServiceImpl implements PruebaGpsService {
    
    
    @Autowired
	private PruebaGCbRepository pruebaGCbRepository;
    
    @Override 
	public boolean grabarGps(PruebaGCb pruebaGCb) throws HttpClientErrorException, ParseException {
		try {
			pruebaGCbRepository.save(pruebaGCb);
			return true;            
        } catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,e.getMessage());
        }
	}
    
}
