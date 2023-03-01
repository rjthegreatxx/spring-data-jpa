package com.kraftwerking.springdatajpa.repository;

import com.kraftwerking.springdatajpa.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository2 {

    // Criteria queries
    public List<Product> findByNameOrDescriptionCriteriaQuery(@Param("name") String name,
                                                              @Param("description") String description);

}