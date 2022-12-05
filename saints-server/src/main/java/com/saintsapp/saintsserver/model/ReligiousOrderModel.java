package ccom.saintsapp.saintsserver.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import ccom.saintsapp.saintsserver.entities.SaintEntity;

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
    private Long id;
    private String religiousOrderName;
    private LocalDate religiousOrderFoundationDate;

    private List<SaintModel> saintsOnOrder;

    private SaintModel orderFounder;
}
