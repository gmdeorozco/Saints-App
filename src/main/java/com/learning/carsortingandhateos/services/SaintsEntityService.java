package com.learning.carsortingandhateos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.carsortingandhateos.entities.SaintEntity;
import com.learning.carsortingandhateos.repository.SaintsRepository;

@Service
public class SaintsEntityService {
    @Autowired
    SaintsRepository saintsRepository;

    public Optional<SaintEntity> getById(Long id){
        return saintsRepository.findById(id);
    }

    public SaintEntity saveSaintEntity( SaintEntity saintEntity ){
        return saintsRepository.save( saintEntity );
    }
}
