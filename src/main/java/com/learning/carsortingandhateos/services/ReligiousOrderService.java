package com.learning.carsortingandhateos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.carsortingandhateos.entities.ReligiousOrderEntity;
import com.learning.carsortingandhateos.repository.ReligiousOrderRepository;

@Service
public class ReligiousOrderService {
    @Autowired
    ReligiousOrderRepository religiousOrderRepository;

    public Optional<ReligiousOrderEntity> getById( Long id ){
        return religiousOrderRepository.findById(id);
    }

    public ReligiousOrderEntity saveReligiousOrderEntity( ReligiousOrderEntity religiousOrderEntity){
        return religiousOrderRepository.save(religiousOrderEntity);
    }
}
