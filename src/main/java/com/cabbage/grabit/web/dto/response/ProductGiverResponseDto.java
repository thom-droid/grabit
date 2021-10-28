package com.cabbage.grabit.web.dto.response;

import com.cabbage.grabit.domain.user.Giver;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductGiverResponseDto {

    private Long id;
    private String name;
    private String picture;
    private String company;

    @Builder
    public ProductGiverResponseDto(Giver giver) {
        this.id = giver.getId();
        this.name = giver.getName();
        this.picture = giver.getPicture();
        this.company = giver.getCompany();
    }
}
