package com.pwel.allegrotrader.product.infrastructure;

import com.pwel.allegrotrader.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%',:name,'%'))")
    List<Product> findProductsByName(String name);

}
