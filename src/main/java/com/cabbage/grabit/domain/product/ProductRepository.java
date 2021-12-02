package com.cabbage.grabit.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findBySaleStatus(boolean saleStatus, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM products p WHERE p.categories LIKE %:categories%")
    Page<Product> findByCategoriesContaining(String categories, Pageable pageable);
}
