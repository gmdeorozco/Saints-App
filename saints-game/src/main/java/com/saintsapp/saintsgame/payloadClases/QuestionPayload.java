package com.saintsapp.saintsgame.payloadClases;

import java.time.Instant;
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
    private Instant creationInstant;
    private Instant timeOutInstant;
    
}
