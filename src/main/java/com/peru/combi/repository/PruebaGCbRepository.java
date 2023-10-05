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

	/*@Transactional(isolation = Isolation.REPEATABLE_READ,value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
	@Query( value ="SELECT  codigoRuta, numeroParada, gps_Coordenadas, nombreParada, truncate((acos(sin(radiansLat1) * sin(radiansLat2) + " 
				 + "cos(radiansLat1) * cos(radiansLat2) * cos(lonDifference)) * 6371000),5) as dif FROM " 
				 + "(SELECT codigoRuta, numeroParada, gps_Coordenadas, nombreParada, ((:latitude * 0.017453292519943295) - " 
				 + "(ST_Y(gps_coordenadas) * 0.017453292519943295)) as lonDifference, (:longitud * 0.017453292519943295) as radiansLat1, " 
				 + "(ST_X(gps_coordenadas) * 0.017453292519943295) as radiansLat2 FROM rutas where codigoRuta = :codigoRuta " 
				 + "and destino = :destino) as t order by 5 limit 1;",nativeQuery = true)
	List<lastLocations> obtenerGpsSegunRuta(@Param("Latitude") String latitude, @Param("Longitud") String longitud, 
											@Param("destino") String destino, @Param("codigoRuta") String codigoRuta);*/

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO pruebagcb (numero_telefono, nombre_Usuario, gps_Coordenadas, fecha_Registro, gps_CoordenadasObtenida) " +
				   "SELECT  :numero_telefono, :nombre_Usuario, t.gps_Coordenadas,:fecha_Registro, ST_PointFromText(:gps_Coordenadas) " + 
				   "FROM (SELECT codigoRuta, numeroParada, gps_Coordenadas, nombreParada, ((:longitud * 0.017453292519943295) - " + 
				   "(ST_Y(gps_coordenadas) * 0.017453292519943295)) as lonDifference, (:latitude * 0.017453292519943295) as radiansLat1, " +
				   "(ST_X(gps_coordenadas) * 0.017453292519943295) as radiansLat2 FROM rutas r where r.codigoRuta = :codigoRuta and " + 
				   "r.destino = :destino) as t order by truncate((acos(sin(radiansLat1) * sin(radiansLat2) + cos(radiansLat1) * " + 
				   "cos(radiansLat2) * cos(lonDifference)) * 6371000),5) limit 1;", nativeQuery = true)
	void saveWithPoint(@Param("numero_telefono") String numero_telefono, @Param("nombre_Usuario") String nombre_Usuario, 
					   @Param("latitude") String latitude, @Param("longitud") String longitud, @Param("codigoRuta") String codigoRuta,
					   @Param("destino") String destino, @Param("gps_Coordenadas") String gps_Coordenadas,  @Param("fecha_Registro") Date fecha_Registro);

					   
					 



}