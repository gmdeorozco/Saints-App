package com.saintsapp.saintsserver.repository;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.saintsapp.saintsserver.View.UserView.Saint;
import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.entities.SaintEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.SetJoin;

@Repository
public class SaintRepositoryWithQueryBuilder {
    
    List<Saint> findSaintsGroupByFounders( String order ){
        EntityManager entityManager;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery< SaintEntity > criteriaQuery = criteriaBuilder.createQuery( SaintEntity.class );

        Root<SaintEntity> saint = criteriaQuery.from( SaintEntity.class );
        SetJoin <SaintEntity, ReligiousOrderEntity> saints = saint.join(SaintEntity.saintReligiousOrder);
    }
    

    
}
