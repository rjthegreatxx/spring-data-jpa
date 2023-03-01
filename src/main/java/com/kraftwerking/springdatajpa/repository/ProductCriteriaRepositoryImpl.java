package com.kraftwerking.springdatajpa.repository;

import com.kraftwerking.springdatajpa.entity.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository2Impl implements ProductRepository2 {

    private EntityManager entityManager;

    // Criteria queries
    public List<Product> findByNameOrDescriptionCriteriaQuery(@Param("name") String name,
                                                 @Param("description") String description){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery  = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery .from(Product.class);

        /*
         *  Adding search criteria's for query using CriteriaBuilder
         */
        List<Predicate> searchCriterias = new ArrayList<>();
        searchCriterias.add( criteriaBuilder.like( root.get("name"), "%"+name+"%") );
        searchCriterias.add( criteriaBuilder.like( root.get("description"), "%"+description+"%") );

        criteriaQuery.select( root ).where( criteriaBuilder.and( searchCriterias.toArray(new Predicate[searchCriterias.size()]) ));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void close() {
        this.entityManager.close();
    }
}