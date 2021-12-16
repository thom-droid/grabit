package com.cabbage.grabit.api.subscription;

import com.cabbage.grabit.domain.subscription.dto.SubscriptionPostRequestDto;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionFacade subscriptionFacade;

    @GetMapping("/list/{takerId}")
    public ApiResult getSubscription(@PathVariable Long takerId,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size
    ){
        return ApiResult.of(ApiStatus.SUCCESS, subscriptionFacade.getSubscriptionByTaker(takerId, page, size));
    }

    @PostMapping
    public ApiResult postSubscription(@RequestBody SubscriptionPostRequestDto requestDto){

        return ApiResult.of(ApiStatus.SUCCESS, subscriptionFacade.postSubscription(requestDto));
    }
}
