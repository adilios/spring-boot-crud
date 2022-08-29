package com.dev.springdata.repositories;

import com.dev.springdata.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByName(String name);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByNameAndDesc(String name, String desc);
    List<Product> findByNameContains(String name);
    List<Product> findByIdIn(List<Integer> ids);
    List<Product> findByIdIn(List<Integer> ids, Pageable pageable);

    //using jpql
    @Query("from Product")
    List<Object[]> findALlProductsPartialData();

    @Query("from Product where name=:name")
    List<Product> findALlProductsByName(String name);

    @Query("from Product where price>:min")
    List<Product> findALlProductsByPrice(@Param("min") double min);

    @Modifying
    @Query("delete from Product where name = :name")
    void deleteProductByName(@Param("name") String name);

}
