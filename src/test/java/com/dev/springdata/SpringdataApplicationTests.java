package com.dev.springdata;

import com.dev.springdata.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.dev.springdata.repositories.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringdataApplicationTests {

    @Autowired
    private ProductRepository repository;

    @Test
    @DisplayName("Deve carregar o contexto")
    void contextLoads() {

    }

    @Test
    @DisplayName("Deve cadastrar um produto corretamente")
    void createProductOnDB_properly() {
        Product product = new Product();
        product.setName("Galaxy s10");
        product.setDesc("Meu celular");
        product.setPrice(2500.00);

        repository.save(product);
    }

    @Test
    void readProductOnDB_properly() {
        Product product = repository.findById(1).get();
        assertEquals("Iphone", product.getName());
    }

    @Test
    void findByName_properly() {
        List<Product> products = repository.findByName("Nokia");
        products.forEach(product -> System.out.print(product.getName()));
    }

    @Test
    void findByNameAndDesc_properly() {
        List<Product> products = repository.findByNameAndDesc("Nokia", "Tijolao");
        products.forEach(product -> System.out.print(product.getPrice() + "\n"));
    }
    @Test
    void findByPrice_properly() {
        List<Product> products = repository.findByPriceGreaterThan(2000.00);
        products.forEach(product -> System.out.print(product.getPrice() + "\n"));
    }
	@Test
    void findByIds_properly() {
        List<Product> products = repository.findByIdIn(Arrays.asList(1,2,3));
        products.forEach(product -> System.out.print(product.getName() + "\n"));
    }

    @Test
    void updateProductOnDB_properly() {
        Product product = repository.findById(1).get();
        product.setPrice(2001d);
        repository.save(product);
    }

    @Test
    void deleteProductOnDB_properly() {
        repository.deleteById(1);
    }
}
