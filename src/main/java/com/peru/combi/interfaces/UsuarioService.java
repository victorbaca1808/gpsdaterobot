package com.peru.combi.interfaces;

import java.text.ParseException;
import org.springframework.web.client.HttpClientErrorException;
import com.peru.combi.clases.Usuario;

public interface UsuarioService {
 
    boolean saveUsuario(Usuario usuario) throws HttpClientErrorException, ParseException;

    boolean updateUsuario(Usuario usuario) throws HttpClientErrorException, ParseException;

    Usuario obtenerUsuario(String num_Telefono, String nombre_Completo);
    
    void obtenerUsuarioByNumberPhone(String num_Telefono);
    
    boolean isActiveServiceUser(String num_Telefono);

}
