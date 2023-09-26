package com.peru.combi.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.peru.combi.clases.PruebaGCb;
import com.peru.combi.clases.lastLocations;

@Repository
@Transactional
public interface PruebaGCbRepository  extends JpaRepository<PruebaGCb, Long> {
    
	@Transactional(isolation = Isolation.REPEATABLE_READ,value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
	@Query( value ="Select gps_Coordenadas as ubication, '' as Direccion from pruebagcb where numero_Telefono = :numTelefono " +
	"order by fecha_Registro desc limit 3;",nativeQuery = true)
	List<lastLocations> obtenerUltimas3Ubicaciones(@Param("numTelefono") String numTelefono);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO pruebagcb (numero_telefono, nombre_Usuario, gps_Coordenadas, fecha_Registro) " +
				   "VALUES (:numero_telefono, :nombre_Usuario, ST_PointFromText(:gps_Coordenadas), :fecha_Registro)", nativeQuery = true)
	void saveWithPoint(@Param("numero_telefono") String numero_telefono, @Param("nombre_Usuario") String nombre_Usuario, 
					   @Param("gps_Coordenadas") String gps_Coordenadas, @Param("fecha_Registro") Date fecha_Registro);


}