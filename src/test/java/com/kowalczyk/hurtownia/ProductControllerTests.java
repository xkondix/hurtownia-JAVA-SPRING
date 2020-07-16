package com.kowalczyk.hurtownia;


import com.kowalczyk.hurtownia.controller.ProductController;
import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.repositories.CategoryRespository;
import com.kowalczyk.hurtownia.model.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private List<Product> productList;

    private Category category;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRespository categoryRespository;

    @Before
    public void setup() {
        category = new Category("Electronics");
        productList = Arrays.asList(
                new Product("Printer","HP",199.99,"code1",category),
                new Product("Mouse","HP",399.99,"code2",category),
                new Product("Computer","Samsung",1999.99,"code3",category)
        );

        productRepository.saveAll(productList);

        when(productRepository.findAll())
                .thenReturn(productList);

        when(productRepository.findById(1l)).thenReturn(Optional.of(new Product("Printer","HP",199.99,"code1",category)));
        when(productRepository.findById(2l)).thenReturn(Optional.of(new Product("Mouse","HP",399.99,"code2",category)));
        when(productRepository.findById(3l)).thenReturn(Optional.of(new Product("Computer","Samsung",1999.99,"code3",category)));


        category.setProducts(Arrays.asList(
                new Product("Printer","HP",199.99,"code1",category),
                new Product("Mouse","HP",399.99,"code2",category),
                new Product("Computer","Samsung",1999.99,"code3",category)
        ));

    }

    @Test
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());

    }

}
