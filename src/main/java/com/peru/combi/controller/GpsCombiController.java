package com.peru.combi.controller;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.peru.combi.clases.EmpresasGrupo;
import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.Respuesta200;
import com.peru.combi.clases.Usuario; 
import com.peru.combi.dto.DriversDto;
import com.peru.combi.dto.UsuarioDto;
import com.peru.combi.interfaces.EmpresasGrupoService;
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
 
    @Autowired
	private EmpresasGrupoService empresasGrupoService;

    @PostMapping(value="/registrarubigeo",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> saveUbication(@Valid @RequestBody List<String> lstUbications) {
        try {
            boolean cerrarRuta = false;  
            for (String dataLocation : lstUbications) {
                String[] aDatos = dataLocation.split("#");
                if (cerrarRuta == false && !usuarioService.isActiveServiceUser(aDatos[0])) {
                    cerrarRuta = true;
                    
                }
                
                int vHour = Integer.parseInt(aDatos[6].substring(0,2));
                int vMinute = Integer.parseInt(aDatos[6].substring(3,5));
                int vSecond = Integer.parseInt(aDatos[6].substring(6,8));
                
                if (aDatos[4].equals("NC") || cerrarRuta) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(format.parse(aDatos[5] + " 00:00:00"));
                    calendar.add(Calendar.SECOND, ((vHour * 60) * 60) + (vMinute * 60) + vSecond);  
                    PruebaGCb pruebaGCb = new PruebaGCb(); 
                    pruebaGCb.setFechaRegistro(calendar.getTime());
                    pruebaGCb.setGpsCoordenadas(aDatos[2] + "/" + aDatos[3]);
                    pruebaGCb.setNumeroTelefono(aDatos[0]);
                    pruebaGCb.setNombreUsuario(aDatos[1]);
                    pruebaGpsService.grabarGps(pruebaGCb);
                }
            }

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());  
            String vFechaRespuesta = 
            (c.get(Calendar.DAY_OF_MONTH) + 1 > 9?"":"0") + 
            String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "/" +  
            String.valueOf((c.get(Calendar.MONTH) + 1 > 9?"":"0")) + String.valueOf(c.get(Calendar.MONTH)+ 1) + "/" +
            String.valueOf(c.get(Calendar.YEAR)) + " " +
            String.valueOf((c.get(Calendar.HOUR) > 9?"":"0") + String.valueOf(c.get(Calendar.HOUR))) + ":" +
            String.valueOf((c.get(Calendar.MINUTE) > 9?"":"0") + String.valueOf(c.get(Calendar.MINUTE))) + ":" + 
            String.valueOf((c.get(Calendar.SECOND) > 9?"":"0") + String.valueOf(c.get(Calendar.SECOND))) + " " +
            String.valueOf(c.get(Calendar.HOUR_OF_DAY)>= 12?"PM":"AM");
            
            if (cerrarRuta) {
                return ResponseEntity.ok().body(new Respuesta200("450", vFechaRespuesta.toString()));
            } else {
                return ResponseEntity.ok().body(new Respuesta200("200",vFechaRespuesta));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }      
    }
    @PostMapping(value="/registrarusuario/{inicio}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> saveUsers(@PathVariable int inicio, 
                                                  @Valid @RequestBody UsuarioDto vUsuario) {
        try {
            Usuario vDatosUsuario = usuarioService.obtenerUsuario(vUsuario.getNumero_Telefono(), vUsuario.getNombre_Usuario());
            Usuario usuario = new Usuario();
            usuario.setNumero_Telefono(vUsuario.getNumero_Telefono());
            usuario.setNombre_Usuario(vUsuario.getNombre_Usuario());
            usuario.setSigla_Grupo(vUsuario.getSigla_Grupo());
            usuario.setServicio_activo(vUsuario.isServicio_Activo());
            usuario.setDriver(vUsuario.isDriver());
            usuario.setCollector(vUsuario.isCollector());
            usuario.setSupervisor(vUsuario.isSupervisor());
            usuario.setUsers(vUsuario.isUsers());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(vUsuario.getFechaInicio())); 
            
            if (vDatosUsuario == null) {
                usuarioService.saveUsuario(usuario,inicio, calendar.getTime());
            } else {
                usuarioService.updateUsuario(usuario,inicio, calendar.getTime());
            }
            return ResponseEntity.ok().body(new Respuesta200("200",""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }      
    }
    
    @PutMapping(value="/pararservicio/{telefono}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> stopService(@PathVariable String telefono) {
        try {
            Date vFecha = usuarioService.terminarRuta(telefono); 
            return ResponseEntity.ok().body(new Respuesta200("200",vFecha.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }      
    }
    
    @GetMapping(value="/obtenerchoferes",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriversDto>> getDrives() {
        try {   
            return ResponseEntity.ok().body(usuarioService.getDriverActives());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }      
    }

    @GetMapping(value="/obtenerRutas",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmpresasGrupo>> getRoots() {
        try {   
            return ResponseEntity.ok().body(empresasGrupoService.getRoots());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }      
    }

    @GetMapping(value="/obtenerFecha",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Respuesta200> getDateServer() {
        try {    
            
            return ResponseEntity.ok().body(empresasGrupoService.getDateServer());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }      
    }

     
}
