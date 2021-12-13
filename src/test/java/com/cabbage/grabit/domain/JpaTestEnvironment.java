package com.cabbage.grabit.domain;

import com.cabbage.grabit.api.subscription.SubscriptionService;
import com.cabbage.grabit.domain.subscription.SubscriptionRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

// with these setup h2 db will be used in spring context
@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTestEnvironment {

    @Autowired
    protected SubscriptionRepository subscriptionRepository;

}
