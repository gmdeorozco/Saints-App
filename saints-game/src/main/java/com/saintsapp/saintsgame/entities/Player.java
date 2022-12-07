package com.saintsapp.saintsgame.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saintsapp.saintsgame.services.EloFormatter;

import lombok.Data;

@Data
public class Player {
    @Autowired @JsonIgnore
    EloFormatter eloFormatter;

    String oldElo;
    String newElo;
    
    public String getEloChange(){
        return ( eloFormatter.format( Double.valueOf(newElo) - Double.valueOf(oldElo) ) );
    }

}