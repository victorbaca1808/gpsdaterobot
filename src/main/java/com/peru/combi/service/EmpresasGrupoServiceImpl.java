package com.peru.combi.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.EmpresasGrupo;
import com.peru.combi.clases.Respuesta200;
import com.peru.combi.interfaces.EmpresasGrupoService;
import com.peru.combi.repository.EmpresasGrupoRepository;

@Service
public class EmpresasGrupoServiceImpl implements EmpresasGrupoService {

    EmpresasGrupoRepository empresasGrupoRepository;

    @Override
    public List<EmpresasGrupo> getRoots() throws HttpClientErrorException, ParseException { 
        try {
			return empresasGrupoRepository.getRoots(); 
        } catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,e.getMessage());
        }
    }

    @Override
    public Respuesta200 getDateServer() throws HttpClientErrorException, ParseException {
        try {
            Respuesta200 vRespuesta200 = new Respuesta200("200",new Date().toString());
			return vRespuesta200;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getDateServer'");
        }
    }
    
    
}
