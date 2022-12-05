package com.saintsapp.saintsserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.saintsapp.saintsserver.assemblers.ReligiousOrderModelAssembler;
import com.saintsapp.saintsserver.assemblers.SaintModelAssembler;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.model.ReligiousOrderModel;
import com.saintsapp.saintsserver.model.SaintModel;
import com.saintsapp.saintsserver.repository.SaintsRepository;
import com.saintsapp.saintsserver.services.ReligiousOrderService;
import com.saintsapp.saintsserver.services.SaintsEntityService;

@RestController
public class WebController {

    @Autowired
    SaintsEntityService saintsEntityService;

    @Autowired
    ReligiousOrderService religiousOrderService;

    @Autowired
    SaintModelAssembler saintModelAssembler;

    @Autowired
    ReligiousOrderModelAssembler religiousOrderModelAssembler;

    @Autowired
	private PagedResourcesAssembler<ReligiousOrderEntity> pagedResourcesAssembler;

    @GetMapping("/api/saints/{id}")
	public ResponseEntity<SaintModel> getSaintById(@PathVariable("id") Long id) 
	{
		return saintsEntityService.getById(id) 
				.map(saintModelAssembler::toModel) 
				.map(ResponseEntity::ok) 
				.orElse(ResponseEntity.notFound().build());
	}

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<ReligiousOrderModel> getReligiousOrderById(@PathVariable("id") Long id) {
        return religiousOrderService.getById(id)
            .map(religiousOrderModelAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        
    }

  

    @GetMapping("/api/saints-list")
	public ResponseEntity<CollectionModel<SaintModel>> getAllSaints(Pageable pageable) 
	{
		List<SaintEntity> orderEntities = saintsEntityService.getAllSaintEntities();
		
		return new ResponseEntity<>(
                saintModelAssembler.toCollectionModel(orderEntities),
                HttpStatus.OK);
	}


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

   
    @GetMapping("/api/saints")
	public ResponseEntity<CollectionModel<SaintModel>> getAllSaints() 
	{
		List<SaintEntity> actorEntities = (List<SaintEntity>) saintsEntityService.getAllSaintEntities();
		
		return new ResponseEntity<>(
				saintModelAssembler.toCollectionModel(actorEntities), 
				HttpStatus.OK);
	}
}
