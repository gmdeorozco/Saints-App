package com.learning.carsortingandhateos.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.learning.carsortingandhateos.controller.WebController;
import com.learning.carsortingandhateos.entities.ReligiousOrderEntity;
import com.learning.carsortingandhateos.entities.SaintEntity;
import com.learning.carsortingandhateos.model.ReligiousOrderModel;
import com.learning.carsortingandhateos.model.SaintModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;

@Component
public class SaintModelAssembler 
    extends RepresentationModelAssemblerSupport< SaintEntity, SaintModel>{
    
    public SaintModelAssembler(){
        super(WebController.class , SaintModel.class );
    }

    @Override
    public SaintModel toModel( SaintEntity entity ){

        SaintModel saintModel = instantiateModel(entity);
        
        saintModel.add( linkTo(
            methodOn(WebController.class )
                .getSaintById( entity.getId()))
            .withSelfRel());

        saintModel.setId( entity.getId());
        saintModel.setOrderFoundedBySaint( toReligiousOrderModel(entity.getOrderFoundedBySaint() ));
        saintModel.setSaintIsApostle(entity.isSaintIsApostle());
        saintModel.setSaintName( entity.getSaintName() );
        saintModel.setSaintPlaceOfBirth( entity.getSaintPlaceOfBirth() );
        
        return saintModel;
    }

    @Override
	public CollectionModel<SaintModel> toCollectionModel(Iterable<? extends SaintEntity> entities) 
	{
		CollectionModel<SaintModel> saintModels = super.toCollectionModel(entities);
		
		saintModels.add(linkTo(methodOn(WebController.class).getAllSaints()).withSelfRel());
		
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
                    methodOn( WebController.class)
                    .getReligiousOrderById( religiousOrderEntity.getId()))
                    .withSelfRel());   
            
    }

}
