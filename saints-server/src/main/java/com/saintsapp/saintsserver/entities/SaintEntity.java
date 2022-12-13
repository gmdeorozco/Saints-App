package com.saintsapp.saintsserver.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString(exclude = {"saintReligiousOrders","ordersFoundedBySaint","friendSaints"})
public class SaintEntity extends Human implements Serializable {
    
    @Id @GeneratedValue
    private Long id;

    private String saintQuote;
    private String saintPlaceOfBirth;
    private CatholicTitle catholicTitle;

    private LocalDate canonizationDate;
    private LocalDate saintFest;

    @ManyToOne
    private Pope canonizedBy;

    @ManyToMany
    @JsonIgnore
    private List<ReligiousOrderEntity> saintReligiousOrders = new ArrayList<ReligiousOrderEntity>();

    @ManyToMany
    @JsonIgnore
 
    public List<ReligiousOrderEntity> ordersFoundedBySaint = new ArrayList<ReligiousOrderEntity>();

   
    @ManyToMany
    @JsonIgnore

    private List<SaintEntity> friendSaints = new ArrayList<SaintEntity>();


    public boolean isOrderFounder(){
        return ordersFoundedBySaint.size() > 0;
    }

    @Builder
    public SaintEntity(Long id, String name, char gender, String saintQuote, String saintPlaceOfBirth,
            CatholicTitle catholicTitle, LocalDate canonizationDate, LocalDate saintFest, Pope canonizedBy,
            List<ReligiousOrderEntity> saintReligiousOrders, List<ReligiousOrderEntity> ordersFoundedBySaint,
            List<SaintEntity> friendSaints) {
        super(id, name, gender);
        this.saintQuote = saintQuote;
        this.saintPlaceOfBirth = saintPlaceOfBirth;
        this.catholicTitle = catholicTitle;
        this.canonizationDate = canonizationDate;
        this.saintFest = saintFest;
        this.canonizedBy = canonizedBy;
        this.saintReligiousOrders = saintReligiousOrders;
        this.ordersFoundedBySaint = ordersFoundedBySaint;
        this.friendSaints = friendSaints;
    }



}
