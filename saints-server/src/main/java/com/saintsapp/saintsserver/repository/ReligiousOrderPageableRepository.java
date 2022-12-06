package com.saintsapp.saintsserver.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;

public interface ReligiousOrderPageableRepository extends PagingAndSortingRepository < ReligiousOrderEntity, Long >{

}