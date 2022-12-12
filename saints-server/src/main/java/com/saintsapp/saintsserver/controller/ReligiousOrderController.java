package com.saintsapp.saintsserver.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsserver.View;
import com.saintsapp.saintsserver.assemblers.ReligiousOrderModelAssembler;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.model.ReligiousOrderModel;
import com.saintsapp.saintsserver.services.ReligiousOrderService;

@RestController
public class ReligiousOrderController {

    private final ReligiousOrderService religiousOrderService;

    @Autowired
    ReligiousOrderModelAssembler religiousOrderModelAssembler;

    @Autowired
	private PagedResourcesAssembler<ReligiousOrderEntity> pagedResourcesAssembler;

    public ReligiousOrderController( ReligiousOrderService religiousOrderService ){
        this.religiousOrderService = religiousOrderService;
    }

    @PostMapping("/api/orders/create")
    public ResponseEntity<ReligiousOrderModel> createReligiousOrderEntity( 
        @RequestBody ReligiousOrderEntity order ){                
       
    return new ResponseEntity<ReligiousOrderModel>(
        religiousOrderModelAssembler
            .toModel(religiousOrderService.saveReligiousOrderEntity( order )),HttpStatus.CREATED); 
               
    }

    @PutMapping("/api/orders/create")
    public ResponseEntity<ReligiousOrderModel> updateReligiousOrderEntity( @RequestBody ReligiousOrderEntity order){                
       
    return new ResponseEntity<ReligiousOrderModel>(
        religiousOrderModelAssembler.toModel(religiousOrderService.saveReligiousOrderEntity( order )),HttpStatus.CREATED); 
               
    }

    @JsonView( View.UserView.Order.class )
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<ReligiousOrderModel> getReligiousOrderById(@PathVariable("id") Long id) {
        return religiousOrderService.getById(id)
            .map(religiousOrderModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        
    }

    @DeleteMapping("/api/orders/{id}/delete")
    public ResponseEntity<ReligiousOrderModel> deleteReligiousOrderEntity( @PathVariable(value = "id") Long id){             
        ResponseEntity<ReligiousOrderModel> rm = religiousOrderService.getById(id)
            .map(religiousOrderModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

        return religiousOrderService.deleteReligiousOrderEntity ( id ) ? rm : ResponseEntity.notFound().build() ;
        
    }

    
   //Use when we work pagination
    @GetMapping("/api/orders-list")
	public ResponseEntity<PagedModel<ReligiousOrderModel>> getAllOrders(Pageable pageable) 
	{
		Page<ReligiousOrderEntity> orderEntities = religiousOrderService.getAllReligiousOrderEntities(pageable);
		
		PagedModel<ReligiousOrderModel> collModel = pagedResourcesAssembler
							.toModel(orderEntities, religiousOrderModelAssembler );
		
		return new ResponseEntity<>(collModel,HttpStatus.OK);
	}

    
    @GetMapping("/api/orders")
	public ResponseEntity<CollectionModel<ReligiousOrderModel>> getAllOrders() 
	{
		List<ReligiousOrderEntity> orderEntities = (List<ReligiousOrderEntity>) religiousOrderService
                    .getAllReligiousOrderEntities();
		
		return new ResponseEntity<>(
				religiousOrderModelAssembler.toCollectionModel(orderEntities), 
				HttpStatus.OK);
	}

    

}
