package com.peru.combi.clases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Respuesta200 {
    
    private String codigo;

    public Respuesta200(String codigo) {
        this.codigo = codigo;        
    }
}
