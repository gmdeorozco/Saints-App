package com.saintsapp.saintsserver.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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
