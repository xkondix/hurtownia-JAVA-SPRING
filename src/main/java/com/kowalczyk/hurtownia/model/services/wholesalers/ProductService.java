package com.kowalczyk.hurtownia.model.services.wholesalers;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.CategoryRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.ProductRepository;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import com.kowalczyk.hurtownia.model.resourceAssembler.ProductRepresentationModelAssembler;
import com.kowalczyk.hurtownia.model.responses.wholesalers.ProductRestModel;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRespository;


    public ProductService(final ProductRepository productRepository, CategoryRepository categoryRespository){
        this.productRepository = productRepository;
        this.categoryRespository = categoryRespository;
    }

    public List<ProductRepresentationModel> getAll(String path)
    {
        return productRepository.findAll().stream().map
                (x -> new ProductRepresentationModelAssembler(path).toModel(x))
                .collect(Collectors.toList());
    }

    public void saveProduct(ProductRestModel product) {
        productRepository.save(mapRestModel(product));
    }

    public ProductRepresentationModel getById(Long id)
    {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
        {
            return new ProductRepresentationModelAssembler
                    ("product").toModel(product.get());
        }

        return null;
    }

    public ProductRepresentationModel getByBrand(String brand)
        {
            Optional<Product> product = productRepository.findByBrand(brand);
            if(product.isPresent())
            {
                return new ProductRepresentationModelAssembler
                        ("product").toModel(product.get());
            }

            return null;

        }



    public ProductRepresentationModel getByName(String name)
    {
        Optional<Product> product = productRepository.findByNameOfProduct(name);
        if(product.isPresent())
        {
            return new ProductRepresentationModelAssembler
                    ("product").toModel(product.get());
        }

        return null;

    }




    //methods

    @SneakyThrows
    private Product mapRestModel(final ProductRestModel model) {
        return new Product(model.getNameOfProduct(),model.getBrand(),model.getPricePerItem()
        ,model.getProductCode(),(categoryRespository.findById(model.getCategoryId())).get());
    }



}
