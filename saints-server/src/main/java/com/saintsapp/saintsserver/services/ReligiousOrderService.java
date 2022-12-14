package com.saintsapp.saintsserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.repository.ReligiousOrderPageableRepository;
import com.saintsapp.saintsserver.repository.ReligiousOrderRepository;
import com.saintsapp.saintsserver.repository.SaintsRepository;

@Service
public class ReligiousOrderService {
    @Autowired
    ReligiousOrderRepository religiousOrderRepository;

    @Autowired
    ReligiousOrderPageableRepository religiousOrderPageableRepository;

    @Autowired
    SaintsRepository saintsRepository;

    public Optional<ReligiousOrderEntity> getById( Long id ){
        return religiousOrderRepository.findById(id);
    }

    public ReligiousOrderEntity saveReligiousOrderEntity( ReligiousOrderEntity religiousOrderEntity){
        return religiousOrderRepository.save(religiousOrderEntity);
    }

    public List<ReligiousOrderEntity> getAllReligiousOrderEntities(){
        return (List<ReligiousOrderEntity>) religiousOrderRepository.findAll();
    }

    public Page< ReligiousOrderEntity > getAllReligiousOrderEntities(Pageable pageable) {
        return religiousOrderPageableRepository.findAll( pageable );
      
    }

    public boolean deleteReligiousOrderEntity(Long id){

        try{
            Optional<ReligiousOrderEntity> opt = religiousOrderRepository.findById(id);
                opt.ifPresent( rel -> {

                    rel.getOrderFoundedBy().stream()
                        .map( saint -> saint.getOrdersFoundedBySaint().remove(rel) );

                    rel.getOrderFoundedBy().clear();
                    
                    rel.getSaintsOnOrder().stream()
                        .forEach( saint -> {
                            saint.getSaintReligiousOrders().clear();
                            saintsRepository.save(saint);
                                    });
        });
        opt.ifPresent(religiousOrderRepository::delete);
        return true;

        } catch( Exception e ){
            System.out.println( e.getMessage() );
            return false;
        }
   
    }
}
