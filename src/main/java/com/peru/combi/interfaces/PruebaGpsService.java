package com.peru.combi.interfaces;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.lastLocations;

public interface PruebaGpsService {
    
	boolean grabarGps(PruebaGCb pruebaGCb) throws HttpClientErrorException, ParseException;
	List<lastLocations> obtenerUltimas3Ubicaciones(String numPhone) throws HttpClientErrorException, ParseException;
}
