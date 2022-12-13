package com.saintsapp.saintsserver.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saintsapp.saintsserver.assemblers.SaintModelAssembler;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.model.SaintModel;
import com.saintsapp.saintsserver.services.ReligiousOrderService;
import com.saintsapp.saintsserver.services.SaintsService;

@RestController
@RequestMapping("/api")
public class SaintsController {
    
@Autowired
SaintsService saintsService;

@Autowired
ReligiousOrderService religiousOrderService;

@Autowired
SaintModelAssembler saintModelAssembler;

@PostMapping("saints/create/{orderId}/{isFounder}")
public ResponseEntity<SaintModel> createSaintEntity( @PathVariable(value = "orderId" ) Long orderId, 
    @PathVariable(value = "isFounder") boolean isFounder, 
    @RequestBody SaintEntity saint){                
                    
    Optional<ReligiousOrderEntity> order = religiousOrderService.getById(orderId);
    order.ifPresent(
        (o) -> {
                    saint.getSaintReligiousOrders().add( o );
                    if (isFounder) {
                        saint.getOrdersFoundedBySaint().add( o );
                        o.getOrderFoundedBy().add( saint );
                    }
                }
                    );
       
    return new ResponseEntity<SaintModel>(
        saintModelAssembler.toModel(saintsService.saveSaintEntity( saint )),HttpStatus.CREATED); 
               
    }

    @GetMapping("saints/{id}/friends")
    public ResponseEntity<CollectionModel<SaintModel>> getSaintFriends(@PathVariable(value = "id") Long id){
        List<SaintEntity> friends = saintsService.getFriendSaints(id); 
        return new ResponseEntity<>(
                saintModelAssembler.toCollectionModelSaintFriends( id, friends ),
                HttpStatus.OK);
    }

    @PutMapping("saints/create")
    public ResponseEntity<SaintModel> updateSaintEntity( @RequestBody SaintEntity saint ){                
           
    return new ResponseEntity<SaintModel>(
        saintModelAssembler.toModel(saintsService.saveSaintEntity( saint )),HttpStatus.CREATED); 
               
    }

    @DeleteMapping("saints/{id}/delete")
    public ResponseEntity<SaintModel> deleteSaintEntity( @PathVariable( value = "id" ) Long id ){    
        
        ResponseEntity<SaintModel> sm = saintsService.getById(id)
            .map( saintModelAssembler::toModelDelete )
            .map( ResponseEntity::ok ) 
            .orElse( ResponseEntity.notFound().build() ) ;   

        return saintsService.deleteSaintEntity( id ) ? sm : ResponseEntity.notFound().build() ;
            
           
    }

    @GetMapping("saints/{id}")
	public ResponseEntity<SaintModel> getSaintById( @PathVariable("id") Long id ) 
	{
		return saintsService.getById( id ) 
				.map( saintModelAssembler::toModel ) 
				.map( ResponseEntity::ok ) 
				.orElse( ResponseEntity.notFound().build() ) ;
	}

    @GetMapping("saints-list")
	public ResponseEntity<CollectionModel<SaintModel>> getAllSaints(Pageable pageable) 
	{
		List<SaintEntity> orderEntities = saintsService.getAllSaintEntities();
		
		return new ResponseEntity<>(
                saintModelAssembler.toCollectionModel( orderEntities ),
                HttpStatus.OK);
	}

    @GetMapping("saints")
	public ResponseEntity<CollectionModel<SaintModel>> getAllSaints() 
	{
		List<SaintEntity> actorEntities = (List<SaintEntity>) saintsService.getAllSaintEntities();
		
		return new ResponseEntity<>(
				saintModelAssembler.toCollectionModel(actorEntities), 
				HttpStatus.OK);
	}

    @PutMapping("saints/addfriends/{id1}/{id2}")
    public ResponseEntity<SaintModel> addFriend( @PathVariable( value = "id1") Long saintId1,
                                                    @PathVariable( value = "id2")  Long saintId2 ){
         return 
            ResponseEntity.ok( saintModelAssembler
                                .toModel( saintsService.addFriend(saintId1, saintId2) ) );
            
    }
}
