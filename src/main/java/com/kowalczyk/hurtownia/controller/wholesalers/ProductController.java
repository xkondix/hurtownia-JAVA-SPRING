package com.kowalczyk.hurtownia.controller.wholesalers;

import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import com.kowalczyk.hurtownia.model.responses.wholesalers.ProductRestModel;
import com.kowalczyk.hurtownia.model.services.wholesalers.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<CollectionModel<ProductRepresentationModel>> getAll() {
        return ResponseEntity.ok(CollectionModel.of(
                productService.getAll("product"),
                linkTo(methodOn(ProductController.class).getAll()).withSelfRel()));

    }

    @PostMapping("product")
    public void addProduct(@RequestBody ProductRestModel product) {
        productService.saveProduct(product);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductRepresentationModel> getById(@PathVariable Long id) {
        ProductRepresentationModel product = productService.getById(id);
        product.add(linkTo(methodOn(ProductController.class).getAll()).withSelfRel());
        return ResponseEntity.ok(product);
    }

//    @GetMapping("product/{name}")
//    public ResponseEntity<ProductRepresentationModel> getByName(@PathVariable String name) {
//        System.out.println(name);
//        ProductRepresentationModel product = productService.getByName(name);
//        product.add(linkTo(methodOn(ProductController.class).getAll()).withSelfRel());
//        return ResponseEntity.ok(product);
//    }

//    @GetMapping("product/{brand}")
//    public ResponseEntity<ProductRepresentationModel> getByBrand(@PathVariable String brand) {
//        ProductRepresentationModel product = productService.getByBrand(brand);
//        product.add(linkTo(methodOn(ProductController.class).getAll()).withSelfRel());
//        return ResponseEntity.ok(product);
//    }

}