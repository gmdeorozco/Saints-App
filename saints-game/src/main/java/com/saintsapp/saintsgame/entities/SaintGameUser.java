package com.saintsapp.saintsgame.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsgame.View;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SaintGameUser {

    @Id @GeneratedValue
    @JsonView(value = View.UserView.User.class) 
    private Long id;

    @Column(unique = true)
    @JsonView(value = View.UserView.User.class) 
    private String nickName;

   
    private int age;


    @ManyToMany
    @JoinTable(
    name = "trivia_right", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "trivia_right_id"))
    private List < SaintGameTrivia > triviaAnsweredCorrectly;

    @ManyToMany
    @JoinTable(
        name = "trivia_wrong", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "trivia_wrong_id"))
    private List < SaintGameTrivia > triviaAnsweredWrong;

   
    @OneToMany(mappedBy="user")
    private List <SaintGameTriviaInteraction> interactions;

    @Builder.Default
    @JsonView(value = View.UserView.User.class) 
    double elo=1500;

    @JsonView(value = View.UserView.User.class) 
    int numberOfAnswers;

    
}
