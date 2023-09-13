package com.peru.combi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.Respuesta200;
import com.peru.combi.clases.lastLocations;
import com.peru.combi.interfaces.PruebaGpsService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GpsCombiController {
    
    @Autowired
	private PruebaGpsService pruebaGpsService;

    @GetMapping(value="/registrarubigeo/{latitud}/{longitud}/{telefono}/{nombre}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> saveUbication(@PathVariable String latitud, 
    @PathVariable String longitud, @PathVariable String telefono, @PathVariable String nombre) {
        try {  
            PruebaGCb pruebaGCb = new PruebaGCb();
            pruebaGCb.setFechaRegistro(new Date());
            pruebaGCb.setGpsCoordenadas(latitud + "/" + longitud);
            pruebaGCb.setNumeroTelefono(telefono);
            pruebaGCb.setNombreUsuario(nombre);
            pruebaGpsService.grabarGps(pruebaGCb); 
            return ResponseEntity.ok().body(new Respuesta200("200"));
        } catch (Exception e) {
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
}
