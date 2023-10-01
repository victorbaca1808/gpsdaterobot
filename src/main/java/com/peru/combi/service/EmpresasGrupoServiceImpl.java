package com.peru.combi.service;

import java.text.ParseException;
import java.util.Calendar;
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
            Calendar c = Calendar.getInstance();
            c.setTime(new Date()); 

            String vFechaRespuesta = 
            (c.get(Calendar.DAY_OF_MONTH) > 9?"":"0") + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "/" +  
            (c.get(Calendar.MONTH) + 1 > 9?"":"0") + String.valueOf(c.get(Calendar.MONTH) + 1) + "/" +
            String.valueOf(c.get(Calendar.YEAR)) + " " +
            (c.get(Calendar.HOUR) > 9?"":"0") + String.valueOf(c.get(Calendar.HOUR)) + ":" +
            (c.get(Calendar.MINUTE) > 9?"":"0") + String.valueOf(c.get(Calendar.MINUTE)) + ":" + 
            (c.get(Calendar.SECOND) > 9?"":"0") + String.valueOf(c.get(Calendar.SECOND)) + " " +
            String.valueOf(c.get(Calendar.HOUR_OF_DAY)>= 12?"PM":"AM");
            
            Respuesta200 vRespuesta200 = new Respuesta200("200",vFechaRespuesta);
			return vRespuesta200;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getDateServer'");
        }
    }
    
    
}
