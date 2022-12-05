package com.saintsapp.saintsserver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @Builder @NoArgsConstructor
@ToString(exclude = {"saintReligiousOrder","orderFoundedBySaint"})
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

    @OneToOne(mappedBy = "orderFounder")
    @JsonIgnore
    private ReligiousOrderEntity orderFoundedBySaint;

}
