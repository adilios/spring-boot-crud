package com.dev.springdata;

import com.dev.springdata.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.dev.springdata.repositories.ProductRepository;

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
		product.setId(1);
		product.setName("Iphone");
		product.setDesc("Smartphone de bobo");
		product.setPrice(5000.00);

		repository.save(product);
	}

	@Test
	void readProductOnDB_properly() {
		Product product = repository.findById(1).get();
		assertEquals("Iphone", product.getName());
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
