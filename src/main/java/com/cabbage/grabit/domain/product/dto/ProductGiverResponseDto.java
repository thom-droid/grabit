package com.cabbage.grabit.domain.product.dto;

import com.cabbage.grabit.domain.user.Giver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGiverResponseDto {

    private Long id;
    private String name;
    private String picture;
    private String company;

    public ProductGiverResponseDto(Giver giver) {
        this.id = giver.getId();
        this.name = giver.getName();
        this.picture = giver.getPicture();
        this.company = giver.getCompany();
    }
}
