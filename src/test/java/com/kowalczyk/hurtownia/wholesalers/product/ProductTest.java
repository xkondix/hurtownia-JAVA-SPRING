package com.kowalczyk.hurtownia.wholesalers.product;

import com.kowalczyk.hurtownia.model.entities.wholesalers.Category;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import com.kowalczyk.hurtownia.model.repositories.wholesalers.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;



    @Test
    public void whenFindByName_thenReturnProduct() {
        // given
        Category category = new Category("Electronics");
        Product product = new Product(
                "Fridge","Samsung"
                ,799.99,"1234",category);
        entityManager.persist(product);
        entityManager.flush();

        // when
        Optional<Product> found = productRepository.findByNameOfProduct(product.getNameOfProduct());

        // then
        assertThat(found.get().getNameOfProduct())
                .isEqualTo(product.getNameOfProduct());
    }

    @Test
    public void whenFindByName_thenReturnProduct2() {

        // then
        assertThat(1)
                .isEqualTo(1);
    }


}
