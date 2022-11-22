package com.learning.carsortingandhateos.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReligiousOrder implements Serializable{

    @Id @GeneratedValue
    private Long id;
    private String religiousOrderName;
    private LocalDate religiousOrderFoundationDate;

    @OneToMany
    private List<SaintEntity> saintsOnOrder;
}
