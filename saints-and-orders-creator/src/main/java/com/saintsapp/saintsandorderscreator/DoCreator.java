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

                        HttpEntity<SaintEntity> saintRequest2 = new HttpEntity<>( 
                                                SaintEntity.builder()
                                                .saintIsApostle(false)
                                                .saintName("Ignatius Loyola")
                                                .saintPlaceOfBirth("Spain")
                                                .saintQuote("love should be shown more in deeds than in words")
                                                .build());

                        ReligiousOrderEntity order = restTemplate.postForObject(
                            "https://8080-gmdeorozco-saintsapp-n7lvhhts40z.ws-us78.gitpod.io/api/orders/create", 
                            orderRequest, 
                            ReligiousOrderEntity.class 
                                                                            );
                            Long orderId = order.getId();
                        SaintEntity saint = restTemplate.postForObject(
                            "https://8080-gmdeorozco-saintsapp-n7lvhhts40z.ws-us78.gitpod.io/api/saints/create/"+orderId+"/false", 
                            saintRequest, 
                            SaintEntity.class);

                        SaintEntity saint2 = restTemplate.postForObject(
                                "https://8080-gmdeorozco-saintsapp-n7lvhhts40z.ws-us78.gitpod.io/api/saints/create/"+orderId+"/true", 
                                saintRequest2, 
                                SaintEntity.class);

                        
                        System.out.println( saint.toString() );
                        System.out.println( saint2.toString() );

                    };

    }
}