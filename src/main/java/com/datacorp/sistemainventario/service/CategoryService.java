package com.datacorp.sistemainventario.service;

import com.datacorp.sistemainventario.dto.CategoryDto;
import com.datacorp.sistemainventario.exceptions.MiException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> getCategorys();
    Optional<CategoryDto> getCategory(Long categoryId);
    void createCategory(CategoryDto categoryDto) throws MiException;
    void deleteCategory(Long categoryId);
}
