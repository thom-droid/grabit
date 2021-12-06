package com.cabbage.grabit.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findBySaleStatus(boolean saleStatus, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM product p WHERE p.sale_status = 'Y' and p.category LIKE %:category%")
    Page<Product> findBySaleStatusAndCategoryContaining(
            @Param("category") String category, Pageable pageable);

    Page<Product> findByGiverId(@Param("giver_id") Long giverId, Pageable pageable);
}
