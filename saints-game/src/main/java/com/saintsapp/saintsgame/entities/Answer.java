package com.saintsapp.saintsgame.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Answer {
    @Id @GeneratedValue
    Long id;

    @Column(unique = true)
    String text;

    @JsonIgnore
    @ManyToMany(mappedBy = "rightAnswers")
    private List<SaintGameTrivia> triviaRight;

    @JsonIgnore
    @ManyToMany(mappedBy = "wrongAnswers")
    private List<SaintGameTrivia> triviaWrong;
}
