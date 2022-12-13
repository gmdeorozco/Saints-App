package com.saintsapp.saintsserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.saintsapp.saintsserver.entities.Pope;

public interface PopeRepository extends CrudRepository< Pope, Long >{
    
}
