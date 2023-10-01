package com.peru.combi.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import com.peru.combi.clases.Usuario;
import com.peru.combi.dto.DriversDto;

public interface UsuarioService {
 
    boolean saveUsuario(Usuario usuario, int inicioRuta, Date pFechaInicio) throws HttpClientErrorException, ParseException;

    boolean updateUsuario(Usuario usuario, int inicioRuta, Date pFechaInicio) throws HttpClientErrorException, ParseException;

    Usuario obtenerUsuario(String num_Telefono, String nombre_Completo);
    
    Date terminarRuta(String num_Telefono);
    
    boolean isActiveServiceUser(String num_Telefono);

    List<DriversDto> getDriverActives();

}
