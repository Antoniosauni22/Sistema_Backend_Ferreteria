package com.datacorp.sistemainventario.service;

import com.datacorp.sistemainventario.dto.ProductDto;
import com.datacorp.sistemainventario.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
   public void createProduct(ProductDto productDto);
   public Optional<Product> getProductById(Long productId);
   public void updateProduct(Long productId,ProductDto productDto);
   public void deleteProduct(Long productId);
   List<ProductDto> getAllProducts();

}
