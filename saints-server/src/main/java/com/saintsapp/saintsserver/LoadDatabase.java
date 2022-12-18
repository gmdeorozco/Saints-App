package com.saintsapp.saintsserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saintsapp.saintsserver.entities.Pope;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;
import com.saintsapp.saintsserver.repository.PopeRepository;
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

    @Autowired
    PopeRepository popeRepository;

    @Bean
    CommandLineRunner iniDatabase(){

                    return args -> {
                        Pope francis = Pope.builder()
                            .name("Frnancis")
                            .canonizedSaints(new ArrayList<>())
                            .gender('M')
                            .build();

                        popeRepository.save(francis);

                        ReligiousOrderEntity jesuitOrder = 
                            ReligiousOrderEntity.builder()
                                .religiousOrderName("Society of Jesus")
                                .religiousOrderFoundationDate(LocalDate.parse("1540-01-01"))
                                .build();

                        jesuitOrder = religiousOrderService.saveReligiousOrderEntity( jesuitOrder );
                        
                        log.info("Preloading... " + jesuitOrder );

                        
                        SaintEntity loyolaIgnatius = 
                            SaintEntity.builder()
                              
                                .name("Ignatius of Loyola")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrders(List.of( jesuitOrder ))
                                .ordersFoundedBySaint( List.of(jesuitOrder) )
                                .canonizedBy(francis)
                                .gender('M')
                                .build();

                        loyolaIgnatius = saintsEntityService.saveSaintEntity( loyolaIgnatius );
                        francis.getCanonizedSaints().add(loyolaIgnatius);

                        popeRepository.save(francis);

                        log.info("Preloading..." + loyolaIgnatius);



                        SaintEntity francisXavier = 
                            SaintEntity.builder()
                                
                                .name("Francis Xavier")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrders( List.of( jesuitOrder ) )
                                .canonizedBy( francis )
                                .build();
                        francisXavier = saintsEntityService.saveSaintEntity(francisXavier);

                        log.info("Preloading..." + francisXavier);       
                                //////
                             saintsEntityService.addFriend(loyolaIgnatius.getId(), francisXavier.getId());
                     SaintEntity pedroClaver = 
                            SaintEntity.builder()
                                
                                .name("Pedro Claver")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrders( List.of( jesuitOrder ) )
                                .canonizedBy( francis )
                                .build();
                        pedroClaver = saintsEntityService.saveSaintEntity( pedroClaver );
                           
                        log.info("Preloading..." + pedroClaver);  
                        
                        saintsEntityService.addFriend(loyolaIgnatius.getId(), pedroClaver.getId());

                        jesuitOrder.getOrderFoundedBy().add(loyolaIgnatius);
                        jesuitOrder.getSaintsOnOrder().add(pedroClaver);
                        jesuitOrder.getSaintsOnOrder().add(francisXavier);
                        jesuitOrder.getSaintsOnOrder().add(loyolaIgnatius);
                        religiousOrderService.saveReligiousOrderEntity( jesuitOrder );

 
                    };

    }
}
