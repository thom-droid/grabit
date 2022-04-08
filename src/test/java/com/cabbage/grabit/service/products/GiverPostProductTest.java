package com.cabbage.grabit.service.products;

import com.cabbage.grabit.domain.product.dto.request.ProductPostRequestDto;
import com.cabbage.grabit.domain.user.Giver;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;

public class GiverPostProductTest extends ServiceTestEnvironment{

    @Test
    public void giverSavePost(){
        Giver mockGiver = Mockito.mock(Giver.class);
        HashSet mockSet = Mockito.mock(HashSet.class);
        ProductPostRequestDto mockDto = Mockito.mock(ProductPostRequestDto.class);
        Mockito.when(productFacade.postProduct(mockDto)).thenReturn(1L);
        Mockito.verify(productFacade.postProduct(mockDto), Mockito.times(1));
    }
}
