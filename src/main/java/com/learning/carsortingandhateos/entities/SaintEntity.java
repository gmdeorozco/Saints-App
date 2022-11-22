package com.learning.carsortingandhateos.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity @Data @AllArgsConstructor @Builder @NoArgsConstructor
public class SaintEntity implements Serializable{
    
    @Id @GeneratedValue
    private Long id;

    private String saintName;
    private String saintQuote;
    private String saintPlaceOfBirth;
    private boolean saintIsApostle;

    @ManyToOne
    private ReligiousOrder saintReligiousOrder;

}
