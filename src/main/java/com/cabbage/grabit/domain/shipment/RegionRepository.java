package com.cabbage.grabit.domain.shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RegionRepository extends JpaRepository<Region, Long> {

}
