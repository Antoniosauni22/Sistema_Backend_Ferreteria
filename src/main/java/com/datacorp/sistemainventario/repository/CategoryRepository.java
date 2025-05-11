package com.datacorp.sistemainventario.repository;

import com.datacorp.sistemainventario.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByCateDescription(String name);
    // Optional<Category> findBycateId(Long Id);
}