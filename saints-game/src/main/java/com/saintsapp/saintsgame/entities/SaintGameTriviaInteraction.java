package com.saintsapp.saintsgame.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsgame.View;

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
    @JsonView(value = { View.UserView.User.class })
    private Long id;

    @ManyToOne
    @JoinColumn(name="trivia_id")
    @JsonView(value = { View.UserView.User.class })
    private SaintGameTrivia trivia;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonView(value = { View.UserView.User.class })
    private SaintGameUser user;

    @JsonView(value = { View.UserView.User.class })
    private Instant start;

    @JsonView(value = { View.UserView.User.class })
    private Instant timeout;

    @JsonView(value = { View.UserView.User.class })
    private Instant answeredInstant;
    
}


