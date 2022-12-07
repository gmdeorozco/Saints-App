package com.saintsapp.saintsgame.controller;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.monitor.GaugeMonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.saintsapp.saintsgame.entities.AnswerPayload;
import com.saintsapp.saintsgame.entities.EloResult;
import com.saintsapp.saintsgame.entities.QuestionPayload;
import com.saintsapp.saintsgame.entities.ResultPayload;
import com.saintsapp.saintsgame.entities.SaintGameTrivia;
import com.saintsapp.saintsgame.entities.SaintGameTriviaInteraction;
import com.saintsapp.saintsgame.entities.SaintGameUser;
import com.saintsapp.saintsgame.repository.InteractionRepository;
import com.saintsapp.saintsgame.repository.TriviaRepository;
import com.saintsapp.saintsgame.repository.UserRepository;

@RestController
public class SaintGameController {
    
@Autowired UserRepository userRepository;
@Autowired TriviaRepository triviaRepository;
@Autowired InteractionRepository interactionRepository;


    @GetMapping("/getquestion/{userid}")
    public ResponseEntity<QuestionPayload> getquestion( @PathVariable( "userid" ) Long userid ){
        SaintGameUser user = userRepository.findById( userid ).get();

        SaintGameTriviaInteraction interaction = interactionRepository.save(SaintGameTriviaInteraction.builder()
            .start( Instant.now() ) 
            .timeout( Instant.now().plusSeconds( 5 ) )
            .user( user )
            .trivia(chooseTrivia( user ))
            .build());
        
        return ResponseEntity.ok( QuestionPayload.builder()
            .interactionId(interaction.getId())
            .question( interaction.getTrivia().getQuestion())
            .answers( 
                Stream.concat( 
                            interaction.getTrivia().getRightAnswers().stream()
                                .map( ans -> ans.getText())
                                .collect(Collectors.toList()) .stream(),
                            interaction.getTrivia().getWrongAnswers().stream()
                                .map( ans -> ans.getText() )
                                .collect(Collectors.toList()).stream() )
                            .toList()
                
            )
            .build());
        
    }

    @PostMapping("/submitanswer")
    public ResponseEntity< ResultPayload > submitAnswer( @RequestBody AnswerPayload answerPayload){
        
        SaintGameTriviaInteraction interaction = 
            interactionRepository.findById(answerPayload.getInteractionId()).get();
        SaintGameUser user =
            userRepository.findById(answerPayload.getUserId()).get();
        
        boolean answeredRight = 
            interaction.getTrivia().getRightAnswers().stream()
                .anyMatch(ans -> ans.getText().equals(answerPayload.getAnswer() ));

        
        RestTemplate restTemplate = new RestTemplate();

        EloResult result = 
        restTemplate
            .getForObject(
                "https://8080-gmdeorozco-eloservice-pyzcjj70ubb.ws-us77.gitpod.io/elo/{elo1}/{elo2}/{ng1}/{ng2}/{won}", 
                EloResult.class, 
                interaction.getTrivia().getElo(), 
                user.getElo(),
                interaction.getTrivia().getNumberOfTimesAnswered(),
                user.getNumberOfAnswers()
                ,answeredRight ? 2 : 1);
        
                interaction.getTrivia().setNumberOfTimesAnswered( interaction.getTrivia().getNumberOfTimesAnswered() + 1);
                user.setNumberOfAnswers( user.getNumberOfAnswers() + 1 );


                interaction.getTrivia().setElo( Double.parseDouble( result.getPlayer1().getNewElo() ) );
                user.setElo( Double.parseDouble( result.getPlayer2().getNewElo() ) );

                

                triviaRepository.save( interaction.getTrivia() );
                userRepository.save( user );
        
        return ResponseEntity.ok ( ResultPayload.builder()
            .answeredRight( answeredRight )
            .userOldElo(  Double.parseDouble(result.getPlayer2().getOldElo()))
            .userNewElo( Double.parseDouble(result.getPlayer2().getNewElo()))
            .questionOldElo(Double.parseDouble( result.getPlayer1().getOldElo() ))
            .questionNewElo( Double.parseDouble( result.getPlayer1().getNewElo() ))
            .userNumberOfGames( user.getNumberOfAnswers() )
            .questionNumberOfGames( interaction.getTrivia().getNumberOfTimesAnswered())
            .build());
    }

    private SaintGameTrivia chooseTrivia( SaintGameUser user ) {
        Long count = triviaRepository.count();
        List < SaintGameTrivia > trivias = ( List < SaintGameTrivia > ) triviaRepository.findAll();
        int b = ( int )( Math.random() * ( ( count - 1 ) - 0 + 1 ) + 0 );  
        return trivias.get( b );
    }
}
