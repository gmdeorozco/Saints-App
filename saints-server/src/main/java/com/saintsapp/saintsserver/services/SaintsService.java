package com.saintsapp.saintsserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.repository.SaintsRepository;

@Service
public class SaintsService {
    @Autowired
    SaintsRepository saintsRepository;

    public Optional<SaintEntity> getById(Long id){
        return saintsRepository.findById(id);
    }

    public SaintEntity saveSaintEntity( SaintEntity saintEntity ){
        return saintsRepository.save( saintEntity );
    }

    public List<SaintEntity> getAllSaintEntities(){
        return (List<SaintEntity>) saintsRepository.findAll();
    }
}
