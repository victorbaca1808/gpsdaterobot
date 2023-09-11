package com.peru.combi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.peru.combi.clases.PruebaGCb;

@Repository
@Transactional
public interface PruebaGCbRepository  extends JpaRepository<PruebaGCb, Long> {
    
}