package com.learning.carsortingandhateos.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.carsortingandhateos.entities.ReligiousOrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
    private Long id;

    private String saintName;
    private String saintQuote;
    private String saintPlaceOfBirth;
    private boolean saintIsApostle;

    private ReligiousOrderModel saintReligiousOrder;

    private ReligiousOrderModel orderFoundedBySaint;
}
