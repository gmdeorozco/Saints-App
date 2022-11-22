package com.learning.carsortingandhateos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.learning.carsortingandhateos.entities.SaintEntity;

public interface SaintsRepository extends PagingAndSortingRepository <SaintEntity, Long> {
    
}


