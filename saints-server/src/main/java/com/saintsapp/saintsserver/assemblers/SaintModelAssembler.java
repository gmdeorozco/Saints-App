package com.saintsapp.saintsserver.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.saintsapp.saintsserver.controller.ReligiousOrderController;
import com.saintsapp.saintsserver.controller.SaintsController;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.model.ReligiousOrderModel;
import com.saintsapp.saintsserver.model.SaintModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;

@Component
public class SaintModelAssembler 
    extends RepresentationModelAssemblerSupport< SaintEntity, SaintModel>{
    
    public SaintModelAssembler(){
        super(SaintsController.class , SaintModel.class );
    }

  
    @Override
    public SaintModel toModel( SaintEntity entity ){

        SaintModel saintModel = instantiateModel(entity);
        
        saintModel.add( linkTo(
            methodOn(SaintsController.class )
                .getSaintById( entity.getId()))
            .withSelfRel());

        saintModel.add( linkTo(
                methodOn(ReligiousOrderController.class )
                    .getReligiousOrderById( entity.getSaintReligiousOrder().getId() ))
                .withRel("saint_religious_order"));
        
        saintModel.add( linkTo(
            methodOn(SaintsController.class )
                .deleteSaintEntity( entity.getId()))
            .withRel("delete"));
        
        saintModel.add( linkTo(
                methodOn(SaintsController.class )
                    .updateSaintEntity( entity ))
                .withRel("update"));
        
        
        saintModel.setId( entity.getId());
        saintModel.setOrderFoundedBySaint( toReligiousOrderModel( entity.getOrderFoundedBySaint() ));
        saintModel.setSaintIsApostle( entity.isSaintIsApostle() );
        saintModel.setSaintName( entity.getSaintName() );
        saintModel.setSaintPlaceOfBirth( entity.getSaintPlaceOfBirth() );
        saintModel.setSaintReligiousOrder( toReligiousOrderModel( entity.getSaintReligiousOrder() ));
        
        return saintModel;
    }

    @Override
	public CollectionModel<SaintModel> toCollectionModel(Iterable<? extends SaintEntity> entities) 
	{
		CollectionModel<SaintModel> saintModels = super.toCollectionModel(entities);
		
		saintModels.add(linkTo(methodOn(SaintsController.class).getAllSaints()).withSelfRel());
		
		return saintModels;
	}

    private ReligiousOrderModel toReligiousOrderModel( ReligiousOrderEntity religiousOrderEntity ) {
        if( religiousOrderEntity == null ){
            return null;
        }

        return ReligiousOrderModel.builder()
            .id( religiousOrderEntity.getId())
            .religiousOrderFoundationDate(religiousOrderEntity.getReligiousOrderFoundationDate())
            .religiousOrderName(religiousOrderEntity.getReligiousOrderName())
            .build()
                .add( linkTo(
                    methodOn( ReligiousOrderController.class)
                    .getReligiousOrderById( religiousOrderEntity.getId()))
                    .withSelfRel());   
            
    }

}
