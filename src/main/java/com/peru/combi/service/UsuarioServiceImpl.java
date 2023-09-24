package com.peru.combi.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.Usuario;
import com.peru.combi.dto.DriversDto;
import com.peru.combi.interfaces.UsuarioService;
import com.peru.combi.repository.UsuarioRepository;
 

@Service
public class UsuarioServiceImpl implements UsuarioService  {
 
    @Autowired
	private UsuarioRepository usuarioRepository;
    
    @Override 
	public boolean saveUsuario(Usuario pUsuario) throws HttpClientErrorException, ParseException {
		try {
			usuarioRepository.save(pUsuario);
			return true;            
        } catch (Exception e) {
            e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,e.getMessage());
        }
	}
 
    @Override
    public boolean updateUsuario(Usuario usuario) throws HttpClientErrorException, ParseException {
        try {
			usuarioRepository.updateUsuario(usuario.getNumero_Telefono(), usuario.getNombre_Usuario(),
            usuario.getSigla_Grupo(), usuario.isDriver(),  
            usuario.isUsers(),usuario.isCollector());
            
            if (usuario.isDriver()) {
                usuarioRepository.updateStateServiceUser(usuario.getNumero_Telefono(), true);
            }

            return true;            
        } catch (Exception e) {
            e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,e.getMessage());
        }
    }

    @Override
    public Usuario obtenerUsuario(String num_Telefono, String nombre_Completo) {
        try {
            return usuarioRepository.obtenerUsuario(num_Telefono, nombre_Completo);            
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuario'");
        }
    }

    @Override
    public void obtenerUsuarioByNumberPhone(String num_Telefono) {
        try {
            usuarioRepository.updateStateServiceUser(num_Telefono, false);            
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuarioByNumberPhone'");
        }
    }

    @Override
    public boolean isActiveServiceUser(String num_Telefono) {
        try {
            Usuario usuario = usuarioRepository.isActiveService(num_Telefono);
            return usuario.isServicio_activo();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuarioByNumberPhone'");
        }
    }

    @Override
    public List<DriversDto> getDriverActives() {
        try {
           return usuarioRepository.getDriverActives();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'getDriverActives'");
        }
    }


}
