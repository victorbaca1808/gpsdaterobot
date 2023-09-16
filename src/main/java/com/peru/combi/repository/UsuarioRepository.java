package com.peru.combi.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.peru.combi.clases.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query( value ="Select numero_Telefono, nombre_Usuario, sigla_Grupo, emisor, enviar_Ubicacion_A_Todos, servicio_activo from usuario where numero_Telefono = :numTelefono and nombre_Usuario = :nombreCompleto", nativeQuery = true)
	Usuario obtenerUsuario(@Param("numTelefono") String numTelefono,@Param("nombreCompleto") String nombreCompleto);

    @Modifying
    @Query( value = "Update usuario Set nombre_Usuario = :nombreUsuario, sigla_Grupo = :siglaGrupo, emisor = :emisor, enviar_Ubicacion_A_Todos = :enviar_Ubicacion where numero_Telefono = :numTelefono ", nativeQuery =  true )
    void updateUsuario(@Param("numTelefono") String numTelefono, @Param("nombreUsuario") String nombreUsuario, @Param("siglaGrupo") String siglaGrupo, @Param("emisor") boolean emisor, @Param("enviar_Ubicacion") boolean enviar_Ubicacion);

    @Modifying
    @Query( value = "Update usuario Set servicio_activo = :status Where numero_Telefono = :numTelefono ", nativeQuery =  true )
    void updateStateServiceUser(@Param("numTelefono") String numTelefono, @Param("status") boolean status);

    @Query( value ="Select numero_Telefono, nombre_Usuario, sigla_Grupo, emisor, enviar_Ubicacion_A_Todos, servicio_activo from usuario where numero_Telefono = :numTelefono", nativeQuery = true)
	Usuario isActiveService(@Param("numTelefono") String numTelefono);

}
