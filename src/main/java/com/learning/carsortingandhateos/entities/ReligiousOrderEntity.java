package com.learning.carsortingandhateos.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReligiousOrderEntity implements Serializable{

    @Id @GeneratedValue
    private Long id;
    private String religiousOrderName;
    private LocalDate religiousOrderFoundationDate;

   // @JsonIgnore
    @OneToMany(mappedBy = "saintReligiousOrder")
    private List<SaintEntity> saintsOnOrder;

   // @JsonIgnore
    @OneToOne
    @JoinColumn(name = "orderFounderId", referencedColumnName = "id")
    private SaintEntity orderFounder;
}
