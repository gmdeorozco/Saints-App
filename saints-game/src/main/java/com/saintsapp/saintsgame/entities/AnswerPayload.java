package com.saintsapp.saintsgame.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class AnswerPayload {
    private Long interactionId;
    private Long userId;
    private String answer;
}
