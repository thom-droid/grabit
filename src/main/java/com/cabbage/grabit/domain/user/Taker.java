package com.cabbage.grabit.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DiscriminatorValue("TAKER")
@Entity
public class Taker extends Member {

    private String nickname;

    @Builder
    public Taker(String name, String email, String picture, Role role, String nickname) {
        super(name, email, picture, Role.ROLE_TAKER);
        this.nickname = nickname;
    }


}
