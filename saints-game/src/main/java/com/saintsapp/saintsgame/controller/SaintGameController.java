package com.saintsapp.saintsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.saintsapp.saintsgame.entities.SaintGameUser;
import com.saintsapp.saintsgame.repository.UserRepository;

@RestController
public class SaintGameController {
    
@Autowired
UserRepository userRepository;

    @GetMapping("/interact/{userid}")
    public ResponseEntity<InteactionPayload> interact( @PathVariable("userid") Long userid ){
        SaintGameUser user = userRepository.findById(userid).get();
        return new ResponseEntity<>()
    }
}
