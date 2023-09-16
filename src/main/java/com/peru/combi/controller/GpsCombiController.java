package com.peru.combi.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.Respuesta200;
import com.peru.combi.clases.Usuario;
import com.peru.combi.clases.lastLocations;
import com.peru.combi.interfaces.PruebaGpsService;
import com.peru.combi.interfaces.UsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GpsCombiController {
    
    @Autowired
	private UsuarioService usuarioService;

    @Autowired
	private PruebaGpsService pruebaGpsService;

    @GetMapping(value="/registrarubigeo/{latitud}/{longitud}/{telefono}/{nombre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> saveUbication(@PathVariable String latitud, 
    @PathVariable String longitud, @PathVariable String telefono, @PathVariable String nombre) {
        try {
            if (usuarioService.isActiveServiceUser(telefono)) {
                PruebaGCb pruebaGCb = new PruebaGCb();
                pruebaGCb.setFechaRegistro(new Date());
                pruebaGCb.setGpsCoordenadas(latitud + "/" + longitud);
                pruebaGCb.setNumeroTelefono(telefono);
                pruebaGCb.setNombreUsuario(nombre);
                pruebaGpsService.grabarGps(pruebaGCb);
                return ResponseEntity.ok().body(new Respuesta200("200"));
            } else {
                return ResponseEntity.ok().body(new Respuesta200("450"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }      
    }

    @GetMapping(value="/obtenerlocalizaciones/{telefono}/{nombre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<lastLocations>> getUbications(@PathVariable String telefono, @PathVariable String nombre) {
        try {   
            return ResponseEntity.ok().body(pruebaGpsService.obtenerUltimas3Ubicaciones(telefono));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }      
    }

    @PostMapping(value="/registrarusuario",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> saveUsers(@Valid @RequestBody Usuario vUsuario) {
        try {
            Usuario vDatosUsuario = usuarioService.obtenerUsuario(vUsuario.getNumero_Telefono(), vUsuario.getNombre_Usuario());
            if (vDatosUsuario == null) {
                usuarioService.saveUsuario(vUsuario);
            } else {
                usuarioService.updateUsuario(vUsuario);
            }
            return ResponseEntity.ok().body(new Respuesta200("200"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }      
    }
    
    @PutMapping(value="/pararservicio/{telefono}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> stopService(@PathVariable String telefono) {
        try {
            usuarioService.obtenerUsuarioByNumberPhone(telefono); 
            return ResponseEntity.ok().body(new Respuesta200("200"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }      
    }

}
