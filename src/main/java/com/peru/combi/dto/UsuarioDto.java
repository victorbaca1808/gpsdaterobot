package com.peru.combi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto { 
     
    private String numero_Telefono; 
    private String nombre_Usuario; 
    private String sigla_Grupo; 
    private boolean driver;  
    private boolean servicio_Activo; 
    private boolean users; 
    private boolean collector;  
    private boolean supervisor;
    private String fechaInicio;


}
