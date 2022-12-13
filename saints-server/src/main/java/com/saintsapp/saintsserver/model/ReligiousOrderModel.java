package com.saintsapp.saintsserver.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsserver.View;
import com.saintsapp.saintsserver.entities.SaintEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@Relation(collectionRelation = "saintsOnOrder", itemRelation = "saintOnOrder")
@JsonInclude(Include.NON_NULL)

public class ReligiousOrderModel extends RepresentationModel < ReligiousOrderModel >{
    
    @JsonView( View.UserView.Order.class )
    private Long id;

    @JsonView( View.UserView.Order.class )
    private String religiousOrderName;

    @JsonView( View.UserView.Order.class )
    private LocalDate religiousOrderFoundationDate;

    @JsonView( View.UserView.Order.class )
    private List<SaintModel> saintsOnOrder;

    @JsonView( View.UserView.Order.class )
    private List<SaintModel> orderFounders;

    @JsonView( View.UserView.Order.class )
    private int countOfSaintsOnOrder;
}
