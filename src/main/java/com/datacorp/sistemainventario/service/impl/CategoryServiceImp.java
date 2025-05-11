package com.datacorp.sistemainventario.service.impl;


import com.datacorp.sistemainventario.dto.CategoryDto;
import com.datacorp.sistemainventario.entities.Category;
import com.datacorp.sistemainventario.entities.Product;
import com.datacorp.sistemainventario.exceptions.MiException;
import com.datacorp.sistemainventario.repository.CategoryRepository;
import com.datacorp.sistemainventario.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategorys() {
        return categoryRepository.findAll().stream().map( category ->
            CategoryDto.builder()
                    .cateId(category.getCateId())
                    .cateDescription(category.getCateDescription())
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> CategoryDto.builder()
                .cateId(category.getCateId())
                .cateDescription(category.getCateDescription())
                .build());
    }

    @Override// para confirmar cambios en la base de datos en caso ocurre excepciones hace un rollback
    public void createCategory(CategoryDto categoryDto)  {
         categoryRepository.findById(categoryDto.getCateId()).
                 ifPresentOrElse(
                         category -> { throw new MiException("Error, la categoria ya existe ");

                         },
                         ()-> { Category category= Category.builder()
                                 .cateDescription(categoryDto.getCateDescription())
                                 .createdAt(categoryDto.getCreatedAt())
                                 .updatedAt(categoryDto.getUpdatedAt())
                                 .createdBy(categoryDto.getCreatedBy())
                                 .updatedBy(categoryDto.getUpdatedBy())
                                 .build();
                                categoryRepository.save(category);

                         }

         );


    }





//    @Override// para confirmar cambios en la base de datos en caso ocurre excepciones hace un rollback
//    public void createCategory(CategoryDto categoryDto) {
//        categoryRepository.findById(categoryDto.getCateId()).
//                ifPresentOrElse(
//                        category-> {throw new RuntimeException("Error la categoria ya existe "); },
//                        ()-> { Category category= Category.builder()
//                                .cateDescription(categoryDto.getCateDescription())
//                                .createdAt(categoryDto.getCreatedAt())
//                                .updatedAt(categoryDto.getUpdatedAt())
//                                .createdBy(categoryDto.getCreatedBy())
//                                .updatedBy(categoryDto.getUpdatedBy())
//                                .build();
//                            categoryRepository.save(category);
//
//                        }
//
//                );
//
//
//    }

//    @Override
//    public void deleteCategory(Long categoryId) {
//        Optional<Category> categoryDtoOptional=categoryRepository.findById(categoryId);
//        if (categoryDtoOptional.isPresent()){
//            categoryRepository.deleteById(categoryDtoOptional.get().getCateId());
//        }
//        else  throw new RuntimeException("Error , La categoria no existe") ;
//    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).ifPresentOrElse(Category->{categoryRepository.deleteById(categoryId); }
                , ()-> {throw  new RuntimeException("Error la categoria no existe ");} ) ;
    }
}
