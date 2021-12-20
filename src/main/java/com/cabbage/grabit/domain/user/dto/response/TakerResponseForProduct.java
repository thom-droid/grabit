package com.cabbage.grabit.domain.user.dto.response;

import com.cabbage.grabit.domain.user.Taker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TakerResponseForProduct {

    private String nickname;
    private String picture;

    public TakerResponseForProduct(Taker taker) {
        this.nickname = taker.getNickname();
        this.picture = taker.getPicture();
    }

    public static TakerResponseForProduct from(Taker taker){
        return TakerResponseForProduct.builder()
                .nickname(taker.getNickname())
                .picture(taker.getPicture())
                .build();
    }
}
