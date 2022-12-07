package com.saintsapp.saintsgame.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class QuestionPayload {
    private Long interactionId;
    private String question;
    private List<String> answers;
    
}
