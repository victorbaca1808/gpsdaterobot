package com.peru.combi.interfaces;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.EmpresasGrupo;
import com.peru.combi.clases.Respuesta200;


public interface EmpresasGrupoService {
    
	List<EmpresasGrupo> getRoots() throws HttpClientErrorException, ParseException;
	Respuesta200 getDateServer() throws HttpClientErrorException, ParseException;
}
