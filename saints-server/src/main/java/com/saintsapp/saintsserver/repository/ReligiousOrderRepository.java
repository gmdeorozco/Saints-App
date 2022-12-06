package com.saintsapp.saintsserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;

public interface ReligiousOrderRepository extends CrudRepository < ReligiousOrderEntity, Long >{

}
