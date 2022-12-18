package com.saintsapp.saintsserver.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass @Data @AllArgsConstructor @NoArgsConstructor
public class Human {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private char gender;

    
}
