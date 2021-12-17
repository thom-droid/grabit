package com.cabbage.grabit.domain.user.dto.response;

import com.cabbage.grabit.domain.user.Giver;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GiverResponseForProduct {

    private Long id;
    private String businessNum;
    private String company;
    private String picture;

    public GiverResponseForProduct(Giver giver) {
        this.id = giver.getId();
        this.businessNum = giver.getBusinessNum();
        this.company = giver.getCompany();
        this.picture = giver.getPicture();
    }
}
