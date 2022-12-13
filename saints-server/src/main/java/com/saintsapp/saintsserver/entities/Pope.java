package com.saintsapp.saintsserver.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsserver.View;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString(exclude = {"canonizedSaints"})
public class Pope extends Human {

    @Id
    private Long id;
    
    @OneToMany
    @JsonView( View.UserView.Order.class )
    private List<SaintEntity> canonizedSaints = new ArrayList<SaintEntity>();

    @Builder 
    public Pope(Long id, String name, char gender, List<SaintEntity> canonizedSaints) {
        super(id, name, gender);
        this.canonizedSaints = canonizedSaints;
    }

    
}
