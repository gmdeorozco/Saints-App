package com.saintsapp.saintsgame.entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaintGameTrivia {
    @Id @GeneratedValue
    Long id;

    String question;

    @ManyToMany
    @JoinTable(
    name = "wrong_ans", 
    joinColumns = @JoinColumn(name = "answer_id"), 
    inverseJoinColumns = @JoinColumn(name = "wrong_ans_id"))
    private List <Answer> wrongAnswers;

    @ManyToMany
    @JoinTable(
    name = "right_ans", 
    joinColumns = @JoinColumn(name = "answer_id"), 
    inverseJoinColumns = @JoinColumn(name = "right_ans_id"))
    private List <Answer> rightAnswers;
    
    
    double elo;
    int numberOfTimesAnswered;

    
    @ManyToMany(mappedBy = "triviaAnsweredCorrectly")
    private List <SaintGameUser> usersWhoAnsweredCorrectly;

    @ManyToMany(mappedBy = "triviaAnsweredWrong")
    private List <SaintGameUser> usersWhoAnsweredWrong;

    @OneToMany(mappedBy = "trivia")
    private List <SaintGameTriviaInteraction> interactions;
    

}
