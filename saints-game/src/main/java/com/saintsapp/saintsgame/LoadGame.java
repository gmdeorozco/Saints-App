package com.saintsapp.saintsgame;
import java.time.Instant;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saintsapp.saintsgame.entities.Answer;
import com.saintsapp.saintsgame.entities.SaintGameTrivia;
import com.saintsapp.saintsgame.entities.SaintGameTriviaInteraction;
import com.saintsapp.saintsgame.entities.SaintGameUser;

@Configuration
public class LoadGame {
    
    @Bean
    CommandLineRunner commandLineRunner(){
        return args ->{
            SaintGameUser user = SaintGameUser.builder()
                .nickName("ernestodeorozco")
                .age(39)
                .numberOfAnswers(0)
                .build();
            
            SaintGameTrivia trivia = SaintGameTrivia.builder()
                .numberOfTimesAnswered(0)
                .question("What is the real name of Ignatius of Loyola?")
                .rightAnswers(List.of( Answer.builder().text("Inigo Lopez de Recalde").build()))
                .wrongAnswers(List.of(
                    Answer.builder().text("Juan Perez").build(),
                    Answer.builder().text("Ernesto Orozco").build(),
                    Answer.builder().text("Ignacio de Avila").build()

                ))
                .build();

            SaintGameTriviaInteraction interaction = SaintGameTriviaInteraction.builder()
                .trivia(trivia)
                .user(user)
                .build();
                
        };
    }
}
