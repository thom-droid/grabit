package com.cabbage.grabit.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Role {

    GIVER("ROLE_GIVER", "판매자_기버"),
    TAKER("ROLE_TAKER", "소비자_테이커");

    String title;
    String description;

    Role(String title, String description){
        this.title = title;
        this.description = description;
    }

}
