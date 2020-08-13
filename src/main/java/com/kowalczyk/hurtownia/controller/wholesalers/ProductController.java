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

    @GetMapping("product/brand/{brand}")
    public ResponseEntity<CollectionModel<ProductRepresentationModel>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(CollectionModel.of(
                productService.getByBrand(brand),
                linkTo(methodOn(ProductController.class).getAll()).withSelfRel()));
    }

    // .getByName(name.replace("_"," ")) if name have whitespace
    // reverse change in class ProductRepresentationModelAssembler, method -> createModelWithId
    // HATEOAS need this
    @GetMapping("product/name/{name}")
    public ResponseEntity<ProductRepresentationModel> getByName(@PathVariable String name) {
        ProductRepresentationModel product = productService.getByName(name.replace("_"," "));
        product.add(linkTo(methodOn(ProductController.class).getAll()).withSelfRel());
        return ResponseEntity.ok(product);
    }

    @PatchMapping("product/{id}")
    public void patchProduct(@RequestBody ProductRestModel productRestModel
            , @PathVariable("employeeId") Long id) {
        productService.patchProduct(productRestModel,id);
    }

    @PutMapping("product/{id}")
    public void putProduct(@RequestBody ProductRestModel productRestModel
            , @PathVariable("employeeId") Long id)
    {
        productService.putProduct(productRestModel,id);
    }

    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }



}