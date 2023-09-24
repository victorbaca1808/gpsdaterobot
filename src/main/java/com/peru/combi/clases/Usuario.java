package com.peru.combi.clases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @Column(name = "numero_Telefono",  nullable = false)
    private String numero_Telefono;

    @Column(name = "nombre_Usuario",  nullable = false)
    private String nombre_Usuario;
    
    @Column(name = "sigla_Grupo",  nullable = false)
    private String sigla_Grupo;

    @Column(name = "driver",  nullable = false)
    private boolean driver; 

    @Column(name = "servicio_activo",  nullable = false)
    private boolean servicio_activo;

    @Column(name = "users",  nullable = false)
    private boolean users;

    @Column(name = "collector",  nullable = false)
    private boolean collector;

    @Column(name = "supervisor",  nullable = false)
    private boolean supervisor;

}
