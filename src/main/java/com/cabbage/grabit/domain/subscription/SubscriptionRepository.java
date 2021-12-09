package com.cabbage.grabit.domain.subscription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Page<Subscription> findAllByTakerId(@Param("taker_id") Long takerId, Pageable paging);

}
