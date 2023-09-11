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
@Table(name = "pruebagcb",schema = "registroclinico")
public class PruebaGCb {

    @Id
	@Column(name = "orden",  nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orden;

    @Column(name = "numero_Telefono",  nullable = false)
    private String numeroTelefono;
     
    @Column(name = "nombre_Usuario",  nullable = false)
    private String nombreUsuario;
    
    @Column(name = "gps_Coordenadas",  nullable = false)
    private String gpsCoordenadas;
    
    @Column(name = "fecha_Registro",  nullable = false)
    private Date fechaRegistro;


}
