package com.peru.combi.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.peru.combi.clases.PruebaGCb;

@Repository
@Transactional
public interface PruebaGCbRepository  extends CrudRepository<PruebaGCb, Long> {
    
}