package com.saintsapp.saintsgame.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SaintGameTriviaInteraction {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="trivia_id")
    private SaintGameTrivia trivia;

    @ManyToOne
    @JoinColumn(name="user_id")
    private SaintGameUser user;

    private Instant start;
    private Instant timeout;
    private Instant answeredInstant;
    
}


