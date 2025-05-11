package com.datacorp.sistemainventario.controller;


import com.datacorp.sistemainventario.dto.ProductDto;
import com.datacorp.sistemainventario.entities.Product;
import com.datacorp.sistemainventario.service.ProductService;
import com.datacorp.sistemainventario.service.impl.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping()
    public ResponseEntity<Void> saveProduct(@RequestBody ProductDto productDto ){
          productService.createProduct(productDto);
          return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        productService.updateProduct(productId,productDto);
        return ResponseEntity.ok().build();

    }


     @DeleteMapping("/{productId}")
     public  ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id){
        productService.deleteProduct(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        // no content porque ha sifo eliminado correctamente el archivo

     }


}
