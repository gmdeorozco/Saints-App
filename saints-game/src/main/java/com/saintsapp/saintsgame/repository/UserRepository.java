package com.saintsapp.saintsgame.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saintsapp.saintsgame.entities.SaintGameUser;

@Repository
public interface UserRepository extends CrudRepository<SaintGameUser, Long>{
    
}
