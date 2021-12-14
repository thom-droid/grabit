package com.cabbage.grabit.domain.reply;

import com.cabbage.grabit.domain.product_review.ProductReview;
import com.cabbage.grabit.domain.user.Giver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "GIVER_ID")
    private Giver giver;

    @MapsId
    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    private ProductReview productReview;

}
