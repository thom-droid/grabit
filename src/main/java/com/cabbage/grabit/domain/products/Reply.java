package com.cabbage.grabit.domain.products;

import com.cabbage.grabit.domain.user.Taker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply {

    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "TAKER_ID")
    private Taker taker;

    @MapsId
    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    private ProductReview productReview;

}
