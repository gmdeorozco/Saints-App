package com.saintsapp.saintsgame.entities;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data @Service
public class EloResult {
    private Player player1;
    private Player player2;
}