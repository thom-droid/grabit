package com.cabbage.grabit.domain.user.dto.response;

import com.cabbage.grabit.domain.user.Taker;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TakerResponseForProduct {

    private String nickname;
    private String picture;

    public TakerResponseForProduct(Taker taker) {
        this.nickname = taker.getNickname();
        this.picture = taker.getPicture();
    }
}
