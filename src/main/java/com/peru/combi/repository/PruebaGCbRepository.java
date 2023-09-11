package com.peru.combi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.peru.combi.clases.PruebaGCb;

@Repository
@Transactional
public interface PruebaGCbRepository  extends CrudRepository<PruebaGCb, Long> {
    
}