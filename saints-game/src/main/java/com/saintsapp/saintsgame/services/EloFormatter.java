package com.saintsapp.saintsgame.services;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

@Service
public class EloFormatter {
    public DecimalFormat df;

    public EloFormatter(){
        this.df = new DecimalFormat("###");
    }

    public String format( double d){
        return df.format(d);
    }
}