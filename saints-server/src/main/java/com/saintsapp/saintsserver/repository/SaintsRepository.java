package com.saintsapp.saintsserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.saintsapp.saintsserver.entities.SaintEntity;

public interface SaintsRepository extends CrudRepository <SaintEntity, Long> {

    
    
}


