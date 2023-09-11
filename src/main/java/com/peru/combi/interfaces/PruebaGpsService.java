package com.peru.combi.interfaces;

import java.text.ParseException;

import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.PruebaGCb;

public interface PruebaGpsService {
    
	boolean grabarGps(PruebaGCb pruebaGCb) throws HttpClientErrorException, ParseException;
}
