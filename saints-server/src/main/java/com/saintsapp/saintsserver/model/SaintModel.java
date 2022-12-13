package com.saintsapp.saintsserver.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.saintsapp.saintsserver.View;
import com.saintsapp.saintsserver.entities.CatholicTitle;
import com.saintsapp.saintsserver.entities.Pope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class SaintModel extends RepresentationModel < SaintModel > {
    @JsonView( View.UserView.Order.class )
    private Long id;

    @JsonView( View.UserView.Order.class )
    private String name;

    @JsonView( View.UserView.Order.class )
    private String saintQuote;

    @JsonView( View.UserView.Order.class )
    private String saintPlaceOfBirth;

    @JsonView( View.UserView.Order.class )
    private CatholicTitle catholicTitle;

    @JsonView( View.UserView.Order.class )
    private LocalDate canonizationDate;

    @JsonView( View.UserView.Order.class )
    private LocalDate saintFest;

    @JsonView( View.UserView.Order.class )
    private Pope canonizedBy;

    @JsonView( View.UserView.Saint.class )
    private List<ReligiousOrderModel> saintReligiousOrders;

    @JsonView( View.UserView.Saint.class )
    private List<ReligiousOrderModel> ordersFoundedBySaint;

    @JsonView( View.UserView.Order.class )
    private boolean isOrderFounder;

    @JsonView( View.UserView.Order.class )
    private List<SaintModel> saintFriends;
   
}
