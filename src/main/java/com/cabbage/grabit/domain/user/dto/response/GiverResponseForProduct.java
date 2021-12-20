package com.cabbage.grabit.domain.user.dto.response;

import com.cabbage.grabit.domain.user.Giver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public static GiverResponseForProduct from(Giver giver){
        return GiverResponseForProduct.builder()
                .id(giver.getId())
                .businessNum(giver.getBusinessNum())
                .company(giver.getCompany())
                .picture(giver.getPicture())
                .build();
    }
}
