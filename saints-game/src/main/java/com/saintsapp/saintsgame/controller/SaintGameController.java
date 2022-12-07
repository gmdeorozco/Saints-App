package com.saintsapp.saintsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.saintsapp.saintsgame.entities.AnswerPayload;
import com.saintsapp.saintsgame.entities.QuestionPayload;
import com.saintsapp.saintsgame.entities.ResultPayload;
import com.saintsapp.saintsgame.services.SaintGameService;

@RestController
@RequestMapping("/game")
public class SaintGameController {
    
@Autowired SaintGameService saintGameService;


    @GetMapping("/getquestion/{userid}")
    public ResponseEntity<QuestionPayload> getquestion( @PathVariable( "userid" ) Long userid ){
        return ResponseEntity.ok( saintGameService.getquestion(userid) );
    }

    @PostMapping("/submitanswer")
    public ResponseEntity< ResultPayload > submitAnswer( @RequestBody AnswerPayload answerPayload){
        return ResponseEntity.ok ( saintGameService.submitAnswer(answerPayload) );
    }

    
}
