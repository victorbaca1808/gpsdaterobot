package com.peru.combi.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.peru.combi.clases.Usuario;
import com.peru.combi.clases.UsuarioRutas;
import com.peru.combi.dto.DriversDto;
import com.peru.combi.interfaces.UsuarioService;
import com.peru.combi.repository.UsuarioRepository;
import com.peru.combi.repository.UsuarioRutasRepository;
 

@Service
public class UsuarioServiceImpl implements UsuarioService  {
 
    @Autowired
	private UsuarioRepository usuarioRepository;
 
    @Autowired
	private UsuarioRutasRepository usuarioRutasRepository;
    
    @Override 
	public boolean saveUsuario(Usuario pUsuario, int inicioRuta, Date pFechaInicio) throws HttpClientErrorException, ParseException {
		try {
			usuarioRepository.save(pUsuario);
            if (inicioRuta == 1) {
                UsuarioRutas usuarioRutas = new UsuarioRutas();
                usuarioRutas.setNumero_Telefono(pUsuario.getNumero_Telefono());
                usuarioRutas.setNombre_Usuario(pUsuario.getNombre_Usuario());
                usuarioRutas.setFecha_Hora_Inicio(pFechaInicio);
                usuarioRutas.setFecha_Hora_Final(null);
                usuarioRutasRepository.save(usuarioRutas);
            }
			return true;            
        } catch (Exception e) {
            e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,e.getMessage());
        }
	}
 
    @Override
    public boolean updateUsuario(Usuario usuario, int inicioRuta, Date pFechaInicio) throws HttpClientErrorException, ParseException {
        try {
			usuarioRepository.updateUsuario(usuario.getNumero_Telefono(), usuario.getNombre_Usuario(),
            usuario.getSigla_Grupo(), usuario.isDriver(),  
            usuario.isUsers(),usuario.isCollector());
            
            if (usuario.isDriver()) {
                usuarioRepository.updateStateServiceUser(usuario.getNumero_Telefono(), true);
            }
            
            if (inicioRuta == 1) {
                UsuarioRutas usuarioRutas = new UsuarioRutas();
                usuarioRutas.setNumero_Telefono(usuario.getNumero_Telefono());
                usuarioRutas.setNombre_Usuario(usuario.getNombre_Usuario());
                usuarioRutas.setFecha_Hora_Inicio(pFechaInicio);
                usuarioRutas.setFecha_Hora_Final(null);
                usuarioRutasRepository.save(usuarioRutas);
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
    public Date terminarRuta(String num_Telefono) {
        try {
            Date vFecha = new Date();
            usuarioRepository.updateStateServiceUser(num_Telefono, false);
            int vOrden = usuarioRutasRepository.getIdUsuarioRutasByNumeroTelefono(num_Telefono);
            usuarioRutasRepository.terminateServiceRoot(vFecha, vOrden);
            return vFecha;
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
            throw new UnsupportedOperationException("Unimplemented method 'isActiveServiceUser'");
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
