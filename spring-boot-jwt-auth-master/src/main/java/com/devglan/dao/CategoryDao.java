package com.devglan.dao;

import java.security.cert.PKIXRevocationChecker.Option;

import com.devglan.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends CrudRepository<Category, Integer> {

    Category findByName(String name);
    Optional<Category> findByCategoryId(int categoryId);

}