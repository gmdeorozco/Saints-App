package com.learning.carsortingandhateos;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.carsortingandhateos.entities.ReligiousOrderEntity;
import com.learning.carsortingandhateos.entities.SaintEntity;
import com.learning.carsortingandhateos.repository.ReligiousOrderRepository;
import com.learning.carsortingandhateos.repository.SaintsRepository;
import com.learning.carsortingandhateos.services.ReligiousOrderService;
import com.learning.carsortingandhateos.services.SaintsEntityService;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger( LoadDatabase.class );

    @Autowired
    SaintsEntityService saintsEntityService;

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
                        
                        log.info("Preloading... " + religiousOrderService.saveReligiousOrderEntity( jesuitOrder ) );

                        SaintEntity loyolaIgnatius = 
                            SaintEntity.builder()
                                .saintIsApostle(false)
                                .saintName("Ignatius of Loyola")
                                .saintPlaceOfBirth("Spain")
                                .saintQuote("Love is shown more in deeds than in words." )
                                .saintReligiousOrder( jesuitOrder )
                                .orderFoundedBySaint( jesuitOrder )
                                .build();

                        log.info("Preloading..." + saintsEntityService.saveSaintEntity( loyolaIgnatius ));

                        

                        CriteriaBuilder cb = em.getCriteriaBuilder();
                        CriteriaQuery<ReligiousOrderEntity> query = cb.createQuery( ReligiousOrderEntity.class );
                        Root<ReligiousOrderEntity> root = query.from(ReligiousOrderEntity.class);
                        query.select(root);
                        root.fetch("saintsOnOrder");
                        //root.fetch("orderFounder");

                        query.where(cb.equal(root.get("id"), 1L));

                        log.info("with criteria builder " + em.createQuery(query).getSingleResult().toString());

                    };

    }
}
