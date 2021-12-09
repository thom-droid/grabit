package com.cabbage.grabit.domain.shipment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    Page<ShippingAddress> findByTakerId(
            @Param("taker_id") Long takerId,
            Pageable pageable
    );

    List<ShippingAddress> findAllByTakerId(@Param("taker_id") Long takerId);
}
