package com.peru.combi.clases;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario_rutas")
public class UsuarioRutas {
 
    @Column(name = "numero_Telefono",  nullable = false)
    private String numero_Telefono;

    @Column(name = "nombre_Usuario",  nullable = false)
    private String nombre_Usuario;
    
    @Column(name = "fecha_Hora_Inicio",  nullable = false)
    private Date fecha_Hora_Inicio;
    
    @Column(name = "fecha_Hora_Final",  nullable = false)
    private Date fecha_Hora_Final;

    @Id
    @Column(name = "orden",  nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orden;
    
}
