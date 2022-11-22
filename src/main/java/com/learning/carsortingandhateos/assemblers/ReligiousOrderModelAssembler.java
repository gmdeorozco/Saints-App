package com.learning.carsortingandhateos.assemblers;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;

import com.learning.carsortingandhateos.controller.WebController;
import com.learning.carsortingandhateos.entities.ReligiousOrderEntity;
import com.learning.carsortingandhateos.entities.SaintEntity;
import com.learning.carsortingandhateos.model.ReligiousOrderModel;
import com.learning.carsortingandhateos.model.SaintModel;

@Component
public class ReligiousOrderModelAssembler 
    extends RepresentationModelAssemblerSupport<ReligiousOrderEntity, ReligiousOrderModel>{

        public ReligiousOrderModelAssembler() {
            super(WebController.class, ReligiousOrderModel.class);
        }

        @Override
        public ReligiousOrderModel toModel( ReligiousOrderEntity entity){
            ReligiousOrderModel religiousOrderModel = instantiateModel(entity);

            religiousOrderModel.add( linkTo(
                methodOn(WebController.class)
                .getReligiousOrderById(entity.getId()))
                .withSelfRel());

            religiousOrderModel.setId( entity.getId());
            religiousOrderModel.setOrderFounder( toSaintModel( entity.getOrderFounder() ) );
            religiousOrderModel.setReligiousOrderFoundationDate( entity.getReligiousOrderFoundationDate());
            religiousOrderModel.setReligiousOrderName( entity.getReligiousOrderName());
            religiousOrderModel.setSaintsOnOrder( toSaintModel(entity.getSaintsOnOrder()));

            return religiousOrderModel;
        }

        @Override
	    public CollectionModel<ReligiousOrderModel> toCollectionModel(Iterable<? extends ReligiousOrderEntity> entities) 
	    {
		    CollectionModel<ReligiousOrderModel> orderModels = super.toCollectionModel(entities);
		
		    orderModels.add(linkTo(methodOn(WebController.class).getAllOrders()).withSelfRel());
		
		    return orderModels;
	    }

        private List<SaintModel> toSaintModel(List<SaintEntity> saints) {
            if (saints.isEmpty())
                return Collections.emptyList();
    
            return saints.stream()
                    .map(saint -> SaintModel.builder()
                            .id(saint.getId())
                            .saintIsApostle(saint.isSaintIsApostle())
                            .saintName(saint.getSaintName())
                            .saintPlaceOfBirth(saint.getSaintPlaceOfBirth())
                            .saintQuote(saint.getSaintQuote())
                            .build()
                            .add(linkTo(
                                    methodOn(WebController.class)
                                    .getSaintById(saint.getId()))
                                    .withSelfRel()))
                    .collect(Collectors.toList());
        }

        private SaintModel toSaintModel(SaintEntity saint) {
            if (saint == null )
                return null;
    
            return SaintModel.builder()
                            .id(saint.getId())
                            .saintIsApostle(saint.isSaintIsApostle())
                            .saintName(saint.getSaintName())
                            .saintPlaceOfBirth(saint.getSaintPlaceOfBirth())
                            .saintQuote(saint.getSaintQuote())
                            .build()
                            .add(linkTo(
                                    methodOn(WebController.class)
                                    .getSaintById(saint.getId()))
                                    .withSelfRel());
        }

}
