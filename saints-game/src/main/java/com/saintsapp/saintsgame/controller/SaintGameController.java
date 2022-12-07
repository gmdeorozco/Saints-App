package com.saintsapp.saintsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.saintsapp.saintsgame.View;
import com.saintsapp.saintsgame.entities.SaintGameTriviaInteraction;
import com.saintsapp.saintsgame.entities.SaintGameUser;
import com.saintsapp.saintsgame.payloadClases.AnswerPayload;
import com.saintsapp.saintsgame.payloadClases.QuestionPayload;
import com.saintsapp.saintsgame.payloadClases.ResultPayload;
import com.saintsapp.saintsgame.services.SaintGameService;

@RestController
@RequestMapping("/game")
public class SaintGameController {
    
@Autowired SaintGameService saintGameService;


    @GetMapping("/users/{id}")
    public ResponseEntity < SaintGameUser > getUserById( @PathVariable(value = "id") Long id){
        return saintGameService.getUserById( id )
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getquestion/{userid}")
    public ResponseEntity<QuestionPayload> getquestion( @PathVariable( "userid" ) Long userid ){
        return ResponseEntity.ok( saintGameService.getquestion(userid) );
    }

    @GetMapping("/getquestion2/{userid}")
    @JsonView(value = View.UserView.User.class) 
    public ResponseEntity< SaintGameTriviaInteraction > getquestion2( @PathVariable( "userid" ) Long userid ){
        return ResponseEntity.ok( saintGameService.getquestion2(userid) );
    }


    @PostMapping("/submitanswer")
    public ResponseEntity< ResultPayload > submitAnswer( @RequestBody AnswerPayload answerPayload){
        return ResponseEntity.ok ( saintGameService.submitAnswer(answerPayload) );
    }

    //TODO Let the client view answers if he already answered or if he is ADMIN

    
}
