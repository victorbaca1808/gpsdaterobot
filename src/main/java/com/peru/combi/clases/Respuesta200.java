package com.peru.combi.clases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Respuesta200 {
    
    private String codigo; 
    private String dato1; 

    public Respuesta200(String codigo, String dato1) {
        this.codigo = codigo;
        this.dato1 = dato1;
    }
 
}
