package com.kowalczyk.hurtownia.controller;

import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.responses.CategoryRestModel;
import com.kowalczyk.hurtownia.model.responses.ProductRestModel;
import com.kowalczyk.hurtownia.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product")
    public Iterable<Product> getAll()
    {
      return productService.getAll();
    }
    @PostMapping("product")
    public void addProduct(@RequestBody ProductRestModel product) {
        productService.saveProduct(product);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<ProductRestModel> getById(@PathVariable Long id)
    {
        return new ResponseEntity<ProductRestModel>(productService.getById(id), HttpStatus.OK);
    }




}
