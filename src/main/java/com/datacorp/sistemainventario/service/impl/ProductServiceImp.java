package com.datacorp.sistemainventario.service.impl;

import com.datacorp.sistemainventario.dto.ProductDto;
import com.datacorp.sistemainventario.entities.Category;
import com.datacorp.sistemainventario.entities.Product;
import com.datacorp.sistemainventario.repository.CategoryRepository;
import com.datacorp.sistemainventario.repository.ProductRepository;
import com.datacorp.sistemainventario.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    @Transactional
    public void createProduct(ProductDto productDto) {

        categoryRepository.findById(productDto.getCateId()).ifPresentOrElse(
             category -> productRepository.findById(productDto.getProdId()).ifPresentOrElse(
                   product -> { throw new RuntimeException("Existe el producto");},
                     () -> {
                         Product product = Product.builder()
                                 .prodId(productDto.getProdId())
                                 .prodName(productDto.getProdName())
                                 .prodDescription(productDto.getProdDescription())
                                 .prodUnitPrice(productDto.getProdUnitPrice())
                                 .prodCurrentStock(productDto.getProdCurrentStock())
                                 .purchasePrice(productDto.getPurchasePrice())
                                 .sellingPrice(productDto.getSellingPrice())
                                 .prodMinStock(productDto.getProdMinStock())
                                 .createdAt(LocalDateTime.now())
                                 .updatedAt(LocalDateTime.now())
                                 .createBy(productDto.getCreateBy())
                                 .updatedBy(productDto.getUpdatedBy())
                                 .cateId(category.getCateId())
                                 .build();
                       productRepository.save(product);}
             ),
             () -> {throw new RuntimeException("No existe la categoria");}
        );

    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return  productRepository.findById(productId);
   }

    @Override
    @Transactional
    public void updateProduct(Long productId, ProductDto productDTO) {
        productRepository.findById(productId).map( product -> {
            product.setProdName(productDTO.getProdName());
            product.setProdDescription(productDTO.getProdDescription());
            product.setProdUnitPrice(productDTO.getProdUnitPrice());
            product.setProdCurrentStock(productDTO.getProdCurrentStock());
            product.setPurchasePrice(productDTO.getPurchasePrice());
            product.setSellingPrice(productDTO.getSellingPrice());
            product.setProdMinStock(productDTO.getProdMinStock());
            product.setUpdatedBy(productDTO.getUpdatedBy());
            product.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(product);
        });

    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId).ifPresentOrElse(
                product -> {productRepository.deleteById(productId);}
                ,()-> { throw  new RuntimeException("Error : El producto no existe ");}

        );

    }

    @Override
    public List<ProductDto> getAllProducts() {
    return   productRepository.findAll().stream().map(product -> ProductDto.builder()
            .prodId(product.getProdId())
            .cateId(product.getCateId())
            .prodName(product.getProdName())
            .prodDescription(product.getProdDescription())
            .prodUnitPrice(product.getProdUnitPrice())
            .build()).collect(Collectors.toList());
    }



//    @Transactional
//    public void createProduct(ProductDto productDto) {
//       Optional<Category> categoryOptional=  categoryRepository.findById(productDto.getCateId());
//       if (categoryOptional.isPresent()){
//          if (productRepository.findById(productDto.getProdId()).isPresent()){
//               throw new RuntimeException("Error el producto ya existe ");
//           } else {
//              Product product=Product.builder()
//                      .prodId(productDto.getProdId())
//                      .prodName(productDto.getProdName())
//                      .prodDescription(productDto.getProdDescription())
//                                 .prodUnitPrice(productDto.getProdUnitPrice())
//                                 .prodCurrentStock(productDto.getProdCurrentStock())
//                                 .purchasePrice(productDto.getPurchasePrice())
//                                 .sellingPrice(productDto.getSellingPrice())
//                                 .prodMinStock(productDto.getProdMinStock())
//                                 .createdAt(LocalDateTime.now())
//                                 .updatedAt(LocalDateTime.now())
//                                 .createBy(productDto.getCreateBy())
//                                 .updatedBy(productDto.getUpdatedBy())
//                                 .cateId(categoryOptional.get().getCateId())
//                                 .build();
//                        productRepository.save(product);
//
//          }
//       }
    //   }





}
