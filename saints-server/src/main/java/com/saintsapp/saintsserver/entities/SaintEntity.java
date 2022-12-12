package com.saintsapp.saintsserver.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @Builder @NoArgsConstructor
@ToString(exclude = {"saintReligiousOrder","orderFoundedBySaint","friendSaints"})
public class SaintEntity implements Serializable {
    
    @Id @GeneratedValue
    private Long id;

    private String saintName;
    private String saintQuote;
    private String saintPlaceOfBirth;
    private boolean saintIsApostle;

    @ManyToOne
    @JsonIgnore
    private ReligiousOrderEntity saintReligiousOrder;

    @OneToOne( mappedBy = "orderFoundedBy" )
    @JsonIgnore
    private ReligiousOrderEntity orderFoundedBySaint;

   
    @ManyToMany
    @JsonIgnore
    @Builder.Default
    private List<SaintEntity> friendSaints = new ArrayList<SaintEntity>();


    public boolean isOrderFounder(){
        return this.getSaintReligiousOrder().getOrderFoundedBy() == this;
    }



}
