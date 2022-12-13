package com.saintsapp.saintsserver.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Pope extends Human {

    @Id
    private Long id;
    
    @OneToMany
    private List<SaintEntity> canonizedSaints;

    @Builder 
    public Pope(Long id, String name, char gender, List<SaintEntity> canonizedSaints) {
        super(id, name, gender);
        this.canonizedSaints = canonizedSaints;
    }

    
}
