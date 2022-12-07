package com.saintsapp.saintsgame.entities;

import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsgame.View;

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
    @JsonView(value = { View.UserView.Admin.class })
    Long id;

    @JsonView(value = { View.UserView.User.class })
    String question;

    @ManyToMany
    @JoinTable(
    name = "wrong_ans", 
    joinColumns = @JoinColumn(name = "answer_id"), 
    inverseJoinColumns = @JoinColumn(name = "wrong_ans_id"))
    @JsonView(value = { View.UserView.Admin.class })
    private List <Answer> wrongAnswers;

    @ManyToMany
    @JoinTable(
    name = "right_ans", 
    joinColumns = @JoinColumn(name = "answer_id"), 
    inverseJoinColumns = @JoinColumn(name = "right_ans_id"))
    @JsonView(value = { View.UserView.Admin.class })
    private List <Answer> rightAnswers;
    
    @Builder.Default
    @JsonView(value = { View.UserView.User.class })
    double elo=1500;

    @JsonView(value = { View.UserView.User.class })
    int numberOfTimesAnswered;

    @JsonView(value = { View.UserView.User.class })
    private List<Answer> getPossibleAnswers(){
        return Stream.concat(this.wrongAnswers.stream(), this.rightAnswers.stream()).toList();
    }

    @JsonView(value = { View.UserView.Admin.class })
    @ManyToMany( mappedBy = "triviaAnsweredCorrectly" )
    private List <SaintGameUser> usersWhoAnsweredCorrectly;

    @JsonView(value = { View.UserView.Admin.class })
    @ManyToMany( mappedBy = "triviaAnsweredWrong" )
    private List <SaintGameUser> usersWhoAnsweredWrong;

    @JsonView(value = { View.UserView.Admin.class })
    @OneToMany( mappedBy = "trivia" )
  
    private List <SaintGameTriviaInteraction> interactions;
    

}
