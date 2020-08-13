package com.kowalczyk.hurtownia.model.services.wholesalers;

import com.kowalczyk.hurtownia.model.entities.employees.Employee;
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

    public void saveProduct(ProductRestModel productRestModel) {
        Product product = mapToEntity(productRestModel);
        productRepository.save(product);
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

    public List<ProductRepresentationModel> getByBrand(String brand)
        {
            return productRepository.findByBrand(brand).stream().map
                    (x -> new ProductRepresentationModelAssembler("product").toModel(x))
                    .collect(Collectors.toList());

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

    public void putProduct(ProductRestModel productRestModel, Long id) {
        Product product = mapToEntity(productRestModel);
        product.setId(id);
        productRepository.save(product);
    }

    public void patchProduct(ProductRestModel productRestModel, Long id) {

        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent())
        {
            Product product = productOptional.get();
            if(productRestModel.getCategoryId()!=null)
            {
                product.setCategory(categoryRespository
                        .findById(productRestModel.getCategoryId()).get());
            }
            if(productRestModel.getPricePerItem()!=null)
            {
                product.setPricePerItem(productRestModel.getPricePerItem());
            }
            productRepository.save(product);
        }

    }


    public void deleteProduct(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
        {
            productRepository.delete(product.get());
        }
        else
        {
            throw new Exception();
        }
    }



    //methods

    @SneakyThrows
    private Product mapToEntity(final ProductRestModel model) {
        return new Product(model.getNameOfProduct(),model.getBrand(),model.getPricePerItem()
        ,model.getProductCode(),(categoryRespository.findById(model.getCategoryId())).get());
    }


}

