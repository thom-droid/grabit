package com.cabbage.grabit.domain.subscription;

import com.cabbage.grabit.domain.JpaTestEnvironment;
import com.cabbage.grabit.domain.subscription.dto.SubscriptionListResponseDto;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class SubscriptionRepositoryTest extends JpaTestEnvironment {

    @Test
    @Transactional
    public void findAllByTakerId() {

        Long takerId = 3L;
        Page<Subscription> list = subscriptionRepository.findAllByTakerId(takerId, PageRequest.of(0, 5));
        list.forEach(subscription -> {
            System.out.println(subscription.getTaker());
        });
        Page<SubscriptionListResponseDto> viewList = list.map(SubscriptionListResponseDto::new);

        System.out.println(viewList.getContent().get(0).getId());
        System.out.println(viewList.getContent().get(0).getProduct().getName());

        assertThat(list.getContent().get(0).getId()).isEqualTo(1L);

    }
}