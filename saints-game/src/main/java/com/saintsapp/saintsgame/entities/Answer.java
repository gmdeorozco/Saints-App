package com.saintsapp.saintsgame.entities;

import java.util.List;

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

    @ManyToMany(mappedBy = "rightAnswers")
    private List<SaintGameTrivia> triviaRight;

    @ManyToMany(mappedBy = "wrongAnswers")
    private List<SaintGameTrivia> triviaWrong;
}
