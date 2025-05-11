package com.datacorp.sistemainventario.controller;

import com.datacorp.sistemainventario.dto.CategoryDto;
import com.datacorp.sistemainventario.dto.ErrorResponse;
import com.datacorp.sistemainventario.exceptions.MiException;
import com.datacorp.sistemainventario.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

   @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategorys());

    }

    @GetMapping("/{cateId}")
    public ResponseEntity<CategoryDto> categoryById(@PathVariable("cateId") Long id ){
        Optional<CategoryDto> categoryDtoOptional= categoryService.getCategory(id);
        return categoryDtoOptional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }


   @PostMapping("/created")
    public ResponseEntity<ErrorResponse> createCategory(@RequestBody CategoryDto categoryDto ){
       categoryService.createCategory(categoryDto); //  controla con el controllerAdvicer generico
       return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long id ){
       categoryService.deleteCategory(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
