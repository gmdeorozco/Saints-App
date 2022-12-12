package com.saintsapp.saintsserver;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.services.ReligiousOrderService;
import com.saintsapp.saintsserver.services.SaintsService;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger( LoadDatabase.class );

    @Autowired
    SaintsService saintsEntityService;

    @Autowired
    ReligiousOrderService religiousOrderService;

    @Autowired
    EntityManager em;

    @Bean
    CommandLineRunner iniDatabase(){

                    return args -> {

                        ReligiousOrderEntity jesuitOrder = 
                            ReligiousOrderEntity.builder()
                                .religiousOrderName("Society of Jesus")
                                .religiousOrderFoundationDate(LocalDate.parse("1540-01-01"))
                                .build();

                        jesuitOrder = religiousOrderService.saveReligiousOrderEntity( jesuitOrder );
                        
                        log.info("Preloading... " + jesuitOrder );

                        
                        SaintEntity loyolaIgnatius = 
                            SaintEntity.builder()
                                .saintIsApostle(false)
                                .saintName("Ignatius of Loyola")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrder( jesuitOrder )
                                .orderFoundedBySaint( jesuitOrder )
                                .build();

                        loyolaIgnatius = saintsEntityService.saveSaintEntity( loyolaIgnatius );

                        log.info("Preloading..." + loyolaIgnatius);



                        SaintEntity francisXavier = 
                            SaintEntity.builder()
                                .saintIsApostle(false)
                                .saintName("Francis Xavier")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrder( jesuitOrder )
                                .build();
                        francisXavier = saintsEntityService.saveSaintEntity(francisXavier);

                        log.info("Preloading..." + francisXavier);       
                                //////
                             saintsEntityService.addFriend(loyolaIgnatius.getId(), francisXavier.getId());
                     SaintEntity pedroClaver = 
                            SaintEntity.builder()
                                .saintIsApostle(false)
                                .saintName("Pedro Claver")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrder( jesuitOrder )
                                .build();
                        pedroClaver = saintsEntityService.saveSaintEntity( pedroClaver );
                           
                        log.info("Preloading..." + pedroClaver);  
                        
                        saintsEntityService.addFriend(loyolaIgnatius.getId(), pedroClaver.getId());

                        jesuitOrder.setOrderFoundedBy(loyolaIgnatius);
                        religiousOrderService.saveReligiousOrderEntity( jesuitOrder );

 
                    };

    }
}
