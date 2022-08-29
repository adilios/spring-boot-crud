package com.dev.springdata.repositories;

import com.dev.springdata.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String name);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByNameAndDesc(String name, String desc);
    List<Product> findByNameContains(String name);
    List<Product> findByIdIn(List<Integer> ids);

}
