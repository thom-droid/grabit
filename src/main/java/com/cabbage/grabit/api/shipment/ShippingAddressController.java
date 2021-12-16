package com.cabbage.grabit.api.shipment;

import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shipping")
public class ShippingAddressController {

    private ShippingAddressFacade shippingAddressFacade;

    @GetMapping("/{id}")
    public ApiResult getTakerAddress(@PathVariable Long takerId,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size
    ){
        return ApiResult.of(ApiStatus.SUCCESS, shippingAddressFacade.getTakerAddress(takerId, page, size));
    }


//    @PutMapping
//    public ApiResult

}
