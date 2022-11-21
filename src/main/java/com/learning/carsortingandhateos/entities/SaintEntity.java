package com.learning.carsortingandhateos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @Builder @NoArgsConstructor
public class SaintEntity {
    
    @Id @GeneratedValue
    private Long id;

    private String saintName;
    private String saintQuote;
    private String placeOfBirth;
    private boolean isApostle;

}
