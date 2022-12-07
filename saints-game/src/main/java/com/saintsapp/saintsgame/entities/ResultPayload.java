package com.saintsapp.saintsgame.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class ResultPayload {
    private boolean answeredRight=false;

    private double userOldElo;
    private double userNewElo;
    private double questionOldElo;
    private double questionNewElo;
    private int userNumberOfGames;
    private int questionNumberOfGames;
}
