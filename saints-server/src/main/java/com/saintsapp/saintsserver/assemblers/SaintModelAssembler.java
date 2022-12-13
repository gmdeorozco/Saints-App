package com.saintsapp.saintsserver.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsserver.controller.ReligiousOrderController;
import com.saintsapp.saintsserver.controller.SaintsController;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.model.ReligiousOrderModel;
import com.saintsapp.saintsserver.model.SaintModel;
import com.saintsapp.saintsserver.repository.SaintsRepository;
import com.saintsapp.saintsserver.services.SaintsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;

@Component
public class SaintModelAssembler 
    extends RepresentationModelAssemblerSupport< SaintEntity, SaintModel>{
    
    public SaintModelAssembler(){
        super(SaintsController.class , SaintModel.class );
    }

    @Autowired
    SaintsService saintsService;
  

    public SaintModel toModelDelete( SaintEntity entity ){
        
        SaintModel saintModel = instantiateModel( entity );

       
        
            saintModel.add( linkTo(
                methodOn(SaintsController.class )
                    .createSaintEntity( entity.getSaintReligiousOrders().get(0).getId(), entity.isOrderFounder(), entity ))
                .withRel("saveItAgainToDatabaseWithThisPayload"));

                saintModel.setId( entity.getId() );
                saintModel.setOrdersFoundedBySaint( toReligiousOrderModel( entity.getOrdersFoundedBySaint() ));
                saintModel.setCatholicTitle ( entity.getCatholicTitle() );
                saintModel.setName( entity.getName() );
                saintModel.setSaintPlaceOfBirth( entity.getSaintPlaceOfBirth() );
                saintModel.setSaintReligiousOrders( toReligiousOrderModel( entity.getSaintReligiousOrders() ) );
                saintModel.setCanonizationDate( entity.getCanonizationDate());
                saintModel.setCanonizedBy( entity.getCanonizedBy());
                saintModel.setSaintFest( entity.getSaintFest());
                
            return saintModel;
     
        
    }

    @Override
    public SaintModel toModel( SaintEntity entity ){

        SaintModel saintModel = instantiateModel( entity );

        
        
        
        saintModel.add( linkTo(
            methodOn(SaintsController.class )
                .getSaintById( entity.getId()))
            .withSelfRel());

        saintModel.add( linkTo(
                methodOn(ReligiousOrderController.class )
                    .getReligiousOrderById( entity.getSaintReligiousOrders().get(0).getId() ))
                .withRel( "saint_religious_order" ) );
        
        saintModel.add( linkTo(
            methodOn(SaintsController.class )
                .deleteSaintEntity( entity.getId()))
            .withRel( "delete" ));
        
        saintModel.add( linkTo(
                methodOn(SaintsController.class )
                    .updateSaintEntity( entity ))
                .withRel("update"));
        
        
        saintModel.setId( entity.getId() );
        saintModel.setOrdersFoundedBySaint( toReligiousOrderModel( entity.getOrdersFoundedBySaint() ));
        saintModel.setCatholicTitle( entity.getCatholicTitle() );
        saintModel.setName( entity.getName() );
        saintModel.setSaintPlaceOfBirth( entity.getSaintPlaceOfBirth() );
        saintModel.setSaintReligiousOrders( toReligiousOrderModel( entity.getSaintReligiousOrders() ) );
        saintModel.setOrderFounder( entity.isOrderFounder() );
        saintModel.setSaintFriends( toSaintModel( entity.getFriendSaints() ) );
        saintModel.setCanonizationDate( entity.getCanonizationDate());
        saintModel.setCanonizedBy( entity.getCanonizedBy());
        saintModel.setSaintFest( entity.getSaintFest());
        
        return saintModel;
    }

    @Override
	public CollectionModel<SaintModel> toCollectionModel(Iterable<? extends SaintEntity> entities) 
	{
		CollectionModel<SaintModel> saintModels = super.toCollectionModel(entities);
		
		saintModels.add(linkTo(methodOn(SaintsController.class).getAllSaints()).withSelfRel());
		
		return saintModels;
	}



	public CollectionModel<SaintModel> toCollectionModelSaintFriends(Long id, Iterable<? extends SaintEntity> entities) 
	{
		CollectionModel<SaintModel> saintModels = super.toCollectionModel(entities);
		
		saintModels.add(linkTo(methodOn(SaintsController.class).getSaintFriends(id)).withSelfRel());
		
		return saintModels;
	}

    public List<ReligiousOrderModel> toReligiousOrderModel( List<ReligiousOrderEntity> religiousOrderEntities ) {
        if( religiousOrderEntities.isEmpty() ){
           new ArrayList<>();
        }

        return religiousOrderEntities.stream()
        .map( order -> ReligiousOrderModel.builder()
            .id( order.getId())
            .religiousOrderFoundationDate(order.getReligiousOrderFoundationDate())
            .religiousOrderName(order.getReligiousOrderName())
            .countOfSaintsOnOrder( order.getSaintsOnOrder().size() )
            .build()
                .add( linkTo(
                    methodOn( ReligiousOrderController.class)
                    .getReligiousOrderById( order.getId()))
                    .withSelfRel())  )
        .collect( Collectors.toList());
         
            
    }

    private List < SaintModel > toSaintModel( List<SaintEntity> saints ) {
        if ( saints == null || saints.isEmpty() )
            return Collections.emptyList();

        return saints.stream()
                .map( saint -> 
                    SaintModel.builder()
                    .id( saint.getId())
                    .name ( saint.getName())
                    .isOrderFounder( saint.isOrderFounder() )
                    .canonizationDate( saint.getCanonizationDate())
                    .canonizedBy( saint.getCanonizedBy())
                    .saintFest( saint.getSaintFest())
                    .build()
                    
                        .add( linkTo(
                            methodOn( SaintsController.class)
                            .getSaintById( saint.getId()))
                            .withSelfRel())
                        .add( linkTo(
                                methodOn(ReligiousOrderController.class )
                                    .getReligiousOrderById( saint.getSaintReligiousOrders().get(0).getId() ))
                                .withRel( "saint_religious_order" ) )
                        
                        .add( linkTo(
                            methodOn(SaintsController.class )
                                .deleteSaintEntity( saint.getId()))
                            .withRel( "delete" ))
                        
                        .add( linkTo(
                                methodOn(SaintsController.class )
                                    .updateSaintEntity( saint ))
                                .withRel("update"))
                )
                
                .collect(Collectors.toList());
    }

}
