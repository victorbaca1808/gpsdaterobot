package com.peru.combi.clases;

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
@Table(name = "pruebagcb")
public class EmpresasGrupo {
    
    @Id
	@Column(name = "codGroup",  nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codGroup;

	@Column(name = "name",  nullable = false)
    private String name;

	@Column(name = "sigla",  nullable = false)
    private String sigla;

}
