package com.kowalczyk.hurtownia;


import com.kowalczyk.hurtownia.controller.ProductController;
import com.kowalczyk.hurtownia.model.entities.Category;
import com.kowalczyk.hurtownia.model.entities.Product;
import com.kowalczyk.hurtownia.model.repositories.CategoryRepository;
import com.kowalczyk.hurtownia.model.repositories.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


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
    private CategoryRepository categoryRespository;

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


//    @Test
//    public void test_get_all_products() throws Exception {
//        mockMvc.perform(get("/api/products"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameOfProduct", Is.is("Printer")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand", Is.is("HP")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].pricePerItem", Is.is(199.99)));
//
//    }
//

    @Test
    public void test()
    {}



}
