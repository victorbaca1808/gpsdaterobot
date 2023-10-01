package com.peru.combi.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.peru.combi.clases.Usuario;
import com.peru.combi.dto.DriversDto;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query( value ="Select numero_Telefono, nombre_Usuario, sigla_Grupo, driver, servicio_activo, users, collector, supervisor from usuario where numero_Telefono = :numTelefono and nombre_Usuario = :nombreCompleto", nativeQuery = true)
	Usuario obtenerUsuario(@Param("numTelefono") String numTelefono,@Param("nombreCompleto") String nombreCompleto);

    @Modifying
    @Query( value = "Update usuario Set nombre_Usuario = :nombreUsuario, sigla_Grupo = :siglaGrupo, driver = :driver, users= :users, collector= :collector where numero_Telefono = :numTelefono ", nativeQuery =  true )
    void updateUsuario(@Param("numTelefono") String numTelefono, @Param("nombreUsuario") String nombreUsuario, 
    @Param("siglaGrupo") String siglaGrupo, @Param("driver") boolean driver,  
    @Param("users") boolean users, @Param("collector") boolean collector);

    @Modifying
    @Query( value = "Update usuario Set servicio_activo = :status Where numero_Telefono = :numTelefono ", nativeQuery =  true )
    void updateStateServiceUser(@Param("numTelefono") String numTelefono, @Param("status") boolean status);

    @Query( value ="Select numero_Telefono, nombre_Usuario, sigla_Grupo, driver, servicio_activo, users, collector, supervisor from usuario where numero_Telefono = :numTelefono", nativeQuery = true)
	Usuario isActiveService(@Param("numTelefono") String numTelefono);

    @Query( value ="Select numero_Telefono as Codigo, nombre_Usuario as Nombre from usuario where driver = 1 and servicio_activo = 1", nativeQuery = true)
	List<DriversDto> getDriverActives();
 

}
