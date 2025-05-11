package com.datacorp.sistemainventario.repository;

import com.datacorp.sistemainventario.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;
// import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest //Le indica a spring clase de prueba para persistencia de jpA
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {

        Category category=Category.builder()

                .cateDescription("Pinturas")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy("francisco pizarro")
                .updatedBy("maria suiza")
                .build();
        testEntityManager.persist(category);
    }

   @Test
   public void findcategoryBycateDescription(){

      Optional<Category> categoryOptional=categoryRepository.findByCateDescription("Pinturas");
      assertTrue(categoryOptional.isPresent(),"La categoria deberia estar presente ");
     assertEquals(categoryOptional.get().getCateDescription(),"Pinturas");



   }

}