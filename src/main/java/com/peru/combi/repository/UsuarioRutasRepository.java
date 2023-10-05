package com.peru.combi.repository;

import java.util.Date;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import com.peru.combi.clases.UsuarioRutas;

@Repository
@Transactional
public interface UsuarioRutasRepository extends JpaRepository<UsuarioRutas, Long> {
    
    @Modifying
    @Query( value = "Update usuario_rutas Set fecha_Hora_Final = :fecha_Hora_Final Where orden = :orden ", nativeQuery =  true )
    void terminateServiceRoot(@Param("fecha_Hora_Final") Date fecha_Hora_Final, @Param("orden") int orden);

    @Query( value ="Select orden from usuario_rutas where numero_Telefono = :numTelefono and fecha_Hora_Final is null ", nativeQuery = true)
	int getIdUsuarioRutasByNumeroTelefono(@Param("numTelefono") String numTelefono);
}
