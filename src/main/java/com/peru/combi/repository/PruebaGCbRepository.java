package com.peru.combi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.lastLocations;

@Repository
@Transactional
public interface PruebaGCbRepository  extends JpaRepository<PruebaGCb, Long> {
    
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Query( value ="Select gps_Coordenadas as ubication, '' as Direccion from pruebagcb where numero_Telefono = :numTelefono " +
	"order by fecha_Registro desc limit 3;",nativeQuery = true)
	List<lastLocations> obtenerUltimas3Ubicaciones(@Param("numTelefono") String numTelefono);

}