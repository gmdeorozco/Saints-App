package com.saintsapp.saintsgame.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsgame.View;

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

    @JsonView(value = View.UserView.User.class) 
    @Column(unique = true)
    String text;

   
    @ManyToMany(mappedBy = "rightAnswers")
    private List<SaintGameTrivia> triviaRight;

    
    @ManyToMany(mappedBy = "wrongAnswers")
    private List<SaintGameTrivia> triviaWrong;
}
