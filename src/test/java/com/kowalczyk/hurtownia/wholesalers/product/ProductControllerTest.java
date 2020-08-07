package com.kowalczyk.hurtownia.wholesalers.product;

import com.kowalczyk.hurtownia.controller.wholesalers.ProductController;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.representationModel.wholesalers.ProductRepresentationModel;
import com.kowalczyk.hurtownia.model.resourceAssembler.ProductRepresentationModelAssembler;
import com.kowalczyk.hurtownia.model.services.wholesalers.ProductService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void givenProduct_whenGetProductById_thenReturnJson()
            throws Exception {

        Category category = new Category("Electronics");
        Product product = new Product(
                "Fridge","Samsung"
                ,799.99,"1234",category);

         ProductRepresentationModel productRepresentationModel =
                 new ProductRepresentationModelAssembler("product").toModel(product);


        given(productService.getById(
                product.getId()))
                .willReturn(productRepresentationModel);

        mvc.perform(get("/api/product/1")

                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameOfProduct", Is.is("Fridge")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].brand", Is.is("Samsung")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].pricePerItem", Is.is(799.99)));
    }

}
