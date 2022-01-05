package com.cabbage.grabit.domain;

import com.cabbage.grabit.domain.product.ProductRepository;
import com.cabbage.grabit.domain.product_review.ProductReviewRepository;
import com.cabbage.grabit.domain.subscription.SubscriptionRepository;
import com.cabbage.grabit.domain.user.TakerRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

// with these setup h2 db will be used in spring context
@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTestEnvironment {

    @Autowired
    protected SubscriptionRepository subscriptionRepository;

    @Autowired
    protected ProductReviewRepository productReviewRepository;

    @Autowired
    protected TakerRepository takerRepository;

    @Autowired
    protected ProductRepository productRepository;
}
