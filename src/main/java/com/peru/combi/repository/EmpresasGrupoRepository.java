package com.peru.combi.repository;

import java.util.List;
 

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 

import com.peru.combi.clases.EmpresasGrupo;

@Repository
@Transactional
public interface EmpresasGrupoRepository  extends JpaRepository<EmpresasGrupo, Long> {
     
    @Query( value ="Select codeGroup, name, sigla from EmpresasGropo", nativeQuery = true)
	List<EmpresasGrupo> getRoots();
}
