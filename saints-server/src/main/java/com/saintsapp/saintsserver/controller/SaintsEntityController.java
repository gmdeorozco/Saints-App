package com.saintsapp.saintsserver.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.services.ReligiousOrderService;
import com.saintsapp.saintsserver.services.SaintsEntityService;

@RestController
public class SaintsEntityController {
    
@Autowired
SaintsEntityService saintsEntityService;

@Autowired
ReligiousOrderService religiousOrderService;

    @GetMapping("/saints")
    public List<SaintEntity> getAllSaintEntities(){
        return saintsEntityService.getAllSaintEntities( );
    }

    @PostMapping("/saints/{orderId}/{isFounder}")
    public SaintEntity createSaintEntity( @PathVariable(value = "orderId" ) Long orderId, 
                @PathVariable(value = "isFounder") boolean isFounder, 
                @RequestBody SaintEntity saint){
                    
                    
                    Optional<ReligiousOrderEntity> order = religiousOrderService.getById(orderId);
                    order.ifPresent(
                        (o) -> {
                            saint.setSaintReligiousOrder( o );
                            if (isFounder) {
                                saint.setOrderFoundedBySaint( o );
                                o.setOrderFounder(saint);
                            }
                        }
                    );
       
                    return saintsEntityService.saveSaintEntity( saint );

    }
}
