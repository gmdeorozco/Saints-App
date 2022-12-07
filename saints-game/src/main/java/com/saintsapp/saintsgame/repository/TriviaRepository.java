package com.saintsapp.saintsgame.repository;

import org.springframework.data.repository.CrudRepository;

import com.saintsapp.saintsgame.entities.SaintGameTrivia;

public interface TriviaRepository extends CrudRepository< SaintGameTrivia, Long>{
    
}
