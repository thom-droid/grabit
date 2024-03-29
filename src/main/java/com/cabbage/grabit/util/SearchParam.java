package com.cabbage.grabit.util;

import lombok.*;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SearchParam {

    private String sort;
    private String keyword;
    private String category;

    private Pageable pageable;

    public static SearchParam of(Pageable pageable, String keyword, String category, String sort) {
        return SearchParam.builder()
                .pageable(pageable)
                .keyword(keyword)
                .category(category)
                .sort(sort)
                .build();
    }
}
