package com.dev.springdata;

import com.dev.springdata.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.dev.springdata.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<Product> products = repository.findByIdIn(Arrays.asList(1, 2, 3));
        products.forEach(product -> System.out.print(product.getName() + "\n"));
    }
//
//    @Test
//    void pageable_properly() {
//        Pageable pageable = PageRequest.of(0, 2);
//        Page<Product> products = repository.findAll(pageable);
//        products.forEach(product -> System.out.print(product.getName() + "\n"));
//    }

    @Test
    void pageableCustom_properly() {
        Pageable pageable = PageRequest.of(2, 2);
        var products = repository.findByIdIn(Arrays.asList(1,2,3,4) , pageable);
        products.forEach(product -> System.out.print(product.getName() + "\n"));
    }
    @Test
    void findAllUsingJPQL_properly() {
        repository.findALlProductsPartialData().forEach(p -> System.out.println(p[0] + " "));
    }
    @Test
    void findALlByFirstName_properly() {
        repository.findALlProductsByName("Nokia").forEach(p -> System.out.println(p));
    }

    @Test
    void findALlByPrice_properly() {
        repository.findALlProductsByPrice(2000.00).forEach(p -> System.out.println(p));
    }

    @Test
    @Transactional
    @Rollback(false)
    void deleteProductByName_properly() {
        repository.deleteProductByName("Nokia");
    }


//    @Test
//    void sort_properly() {
//        var orders = new ArrayList<Sort.Order>();
//        Sort.Order nameOrder = new Sort.Order(Sort.Direction.DESC, "name");
//        Sort.Order priceOrder = new Sort.Order(Sort.Direction.DESC, "price");
//        orders.add(nameOrder);
//        orders.add(priceOrder);
//
//        repository.findAll(Sort.by(orders))
//                .forEach((product -> System.out.print(product.getName() + " " + product.getPrice() + "\n")));
//    }
//
//    @Test
//    void pageableWithSort_properly() {
//        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "name");
//        repository.findAll(pageable)
//                .forEach((product -> System.out.print(product.getName() + " " + product.getPrice() + "\n")));
//    }

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
