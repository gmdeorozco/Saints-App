package com.saintsapp.saintsgame;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saintsapp.saintsgame.entities.Answer;
import com.saintsapp.saintsgame.entities.SaintGameTrivia;
import com.saintsapp.saintsgame.entities.SaintGameTriviaInteraction;
import com.saintsapp.saintsgame.entities.SaintGameUser;
import com.saintsapp.saintsgame.repository.AnswerRepository;
import com.saintsapp.saintsgame.repository.TriviaRepository;
import com.saintsapp.saintsgame.repository.UserRepository;

@Configuration
public class LoadGame {

    @Autowired UserRepository userRepository;
    @Autowired TriviaRepository triviaRepository;
    @Autowired AnswerRepository answerRepository;
    
    @Bean
    CommandLineRunner commandLineRunner(){
        return args ->{
            SaintGameUser user = SaintGameUser.builder()
                .nickName("ernestodeorozco")
                .age(39)
                .numberOfAnswers(0)
                .elo(1500)
                .build();
            
            System.out.println( userRepository.save(user).toString() );
            
            SaintGameTrivia trivia = SaintGameTrivia.builder()
                .numberOfTimesAnswered(0)
                .question("What is the real name of Ignatius of Loyola?")
                .elo(1500)
                .rightAnswers(List.of( 
                    answerRepository.save(Answer.builder().text("Inigo Lopez de Recalde").build()) 
                    ))
                .wrongAnswers(List.of(
                    answerRepository.save(Answer.builder().text("Juan Perez").build()),
                    answerRepository.save(Answer.builder().text("Ernesto Orozco").build()),
                    answerRepository.save(Answer.builder().text("Ignacio de Avila").build())

                ))
                .build();
            
            System.out.println( triviaRepository.save( trivia ).toString() );

        };
    }
}
