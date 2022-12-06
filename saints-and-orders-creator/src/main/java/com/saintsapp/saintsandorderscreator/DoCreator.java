package com.saintsapp.saintsandorderscreator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DoCreator {
    private static final Logger log = LoggerFactory.getLogger( DoCreator.class );

    @Bean
    CommandLineRunner iniDatabase(){

                    return args -> {

                        RestTemplate restTemplate = new RestTemplate();

                        HttpEntity<ReligiousOrderEntity> orderRequest = new HttpEntity<>( 
                                            ReligiousOrderEntity.builder()
                                            .religiousOrderFoundationDate(LocalDate.parse("1540-01-01"))
                                            .religiousOrderName("Society of Jesus")
                                        .build()
                         );

                        HttpEntity<SaintEntity> saintRequest = new HttpEntity<>( 
                                            SaintEntity.builder()
                                            .saintIsApostle(false)
                                            .saintName("Peter Faber")
                                            .saintPlaceOfBirth("France")
                                            .saintQuote(null)
                                            .build());

                        ReligiousOrderEntity order = restTemplate.postForObject(
                            "https://8080-gmdeorozco-saintsapp-n7lvhhts40z.ws-us77.gitpod.io/api/orders/create", 
                            orderRequest, ReligiousOrderEntity.class 
                                                                            );
                            Long orderId = order.getId();
                        SaintEntity saint = restTemplate.postForObject(
                                    "https://8080-gmdeorozco-saintsapp-n7lvhhts40z.ws-us77.gitpod.io/api/saints/create/"+orderId+"/false", 
                                    saintRequest, 
                                    SaintEntity.class);

                        
                        System.out.println( saint.toString() );

                    };

    }
}