package com.peru.combi.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.lastLocations;
import com.peru.combi.interfaces.PruebaGpsService;
import com.peru.combi.repository.PruebaGCbRepository;

@Service
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

	@Override
	public List<lastLocations> obtenerUltimas3Ubicaciones(String numPhone)
			throws HttpClientErrorException, ParseException { 
		try {
			return pruebaGCbRepository.obtenerUltimas3Ubicaciones(numPhone);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,e.getMessage());
		}
	}
    
}
