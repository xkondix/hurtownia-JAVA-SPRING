package com.kowalczyk.hurtownia.wholesalers.product;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.CategoryRepository;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.ProductRepository;
import com.kowalczyk.hurtownia.model.services.wholesalers.ProductService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;


    @Before
    public void setUp() {
        Category category = new Category("Electronics");
        Product product = new Product(
                "Fridge","Samsung"
                ,799.99,"1234",category);

        Mockito.when(productRepository.findByNameOfProduct(product.getNameOfProduct()).get())
                .thenReturn(product);
    }

    @Test
    public void whenValidName_thenProductShouldBeFound() {
        String name = "Fridge";
        Optional<Product> found = productRepository.findByNameOfProduct(name);

        assertThat(found.get().getNameOfProduct())
                .isEqualTo(name);
    }

}
