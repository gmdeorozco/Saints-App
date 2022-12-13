package com.saintsapp.saintsserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.repository.SaintsRepository;

import jakarta.transaction.Transactional;

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

    
    public List<SaintEntity> getFriendSaints( Long id ){
        return saintsRepository.findById( id )
            .map( saint -> saint.getFriendSaints())
            .orElse( List.of());           
            
    }

    @Transactional
    public SaintEntity addFriend( Long saintId1, Long saintId2 ){
        SaintEntity saint1 = saintsRepository.findById( saintId1 ).get();
        SaintEntity saint2 = saintsRepository.findById( saintId2 ).get();

        saint1.getFriendSaints().add( saint2 );
        saint2.getFriendSaints().add( saint1 );

        saintsRepository.save(saint1);
        saintsRepository.save(saint2);

        return saint1;
    }

    public boolean deleteSaintEntity( Long id ){
       
      try{
        Optional< SaintEntity > opt = saintsRepository.findById( id );
            opt.ifPresent( saint -> {
                
                saint.getOrdersFoundedBySaint().stream()
                    .map( order -> order.getOrderFoundedBy().remove( saint ));
                
                saint.getOrdersFoundedBySaint().clear();

                saint.getSaintReligiousOrders().stream()
                    .map( order -> order.getSaintsOnOrder().remove( saint ));

                saint.getSaintReligiousOrders().clear();

                saint.getFriendSaints().stream()
                    .map( friend -> friend.getFriendSaints().remove( saint ));

                saint.getFriendSaints().clear();
                
                saintsRepository.delete( saint );
            });
        return true;
      } 
      catch ( Exception e ){
        System.out.println( e.getMessage() );
        return false;
      }
      
    }

    public List<ReligiousOrderEntity> getSaintsOrders(Long id) {
        return saintsRepository.findById(id)
            .map( saint -> saint.getSaintReligiousOrders()  )
            .get();
    }
}
