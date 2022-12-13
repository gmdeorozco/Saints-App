package com.saintsapp.saintsserver.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;

import com.saintsapp.saintsserver.controller.ReligiousOrderController;
import com.saintsapp.saintsserver.controller.SaintsController;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.model.ReligiousOrderModel;
import com.saintsapp.saintsserver.model.SaintModel;

@Component
public class ReligiousOrderModelAssembler 
    extends RepresentationModelAssemblerSupport<ReligiousOrderEntity, ReligiousOrderModel>{

        public ReligiousOrderModelAssembler() {
            super(ReligiousOrderController.class, ReligiousOrderModel.class);
        }

        @Autowired
        SaintModelAssembler saintModelAssembler;

        @Override
        public ReligiousOrderModel toModel( ReligiousOrderEntity entity){
            ReligiousOrderModel religiousOrderModel = instantiateModel(entity);

            religiousOrderModel.add( linkTo(
                methodOn(ReligiousOrderController.class)
                .getReligiousOrderById(entity.getId()))
                .withSelfRel());

            religiousOrderModel.add( linkTo(
                methodOn( ReligiousOrderController.class )
                .deleteReligiousOrderEntity(entity.getId()))
                .withRel("deleteOrder")
            ); 

            religiousOrderModel.add( 
                linkTo( 
                    methodOn( ReligiousOrderController.class )
                    .updateReligiousOrderEntity(entity)
                 ).withRel("update")
             );

            religiousOrderModel.add(
                linkTo(
                    methodOn( ReligiousOrderController.class )
                    .getAllOrders()
                 ).withRel("allOrders")
            );


        
           

            religiousOrderModel.setId( entity.getId() );
            religiousOrderModel.setOrderFounders( toSaintModel(entity.getOrderFoundedBy() ));
            religiousOrderModel.setReligiousOrderFoundationDate( entity.getReligiousOrderFoundationDate());
            religiousOrderModel.setReligiousOrderName( entity.getReligiousOrderName());
            religiousOrderModel.setSaintsOnOrder( toSaintModel(entity.getSaintsOnOrder()));
            religiousOrderModel.setCountOfSaintsOnOrder( entity.getSaintsOnOrder().size() );

            return religiousOrderModel;
        }

        @Override
	    public CollectionModel<ReligiousOrderModel> toCollectionModel(Iterable<? extends ReligiousOrderEntity> entities) 
	    {
		    CollectionModel<ReligiousOrderModel> orderModels = super.toCollectionModel(entities);
		
		    orderModels.add(linkTo(methodOn(ReligiousOrderController.class).getAllOrders()).withSelfRel());
		
		    return orderModels;
	    }

        private List < SaintModel > toSaintModel( List<SaintEntity> saints ) {
            if ( saints == null || saints.isEmpty() )
                return Collections.emptyList();
    
            return saints.stream()
                    .map( saint -> saintModelAssembler.toModel( saint ) )
                    .collect( Collectors.toList() );
        }

        

}
