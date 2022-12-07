package com.saintsapp.saintsgame.repository;

import org.springframework.data.repository.CrudRepository;

import com.saintsapp.saintsgame.entities.SaintGameTriviaInteraction;

public interface InteractionRepository extends CrudRepository< SaintGameTriviaInteraction, Long >{
    
}
