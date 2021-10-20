package com.cabbage.grabit.domain.products;

import com.cabbage.grabit.domain.user.Taker;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Replies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TAKER_ID")
    private Taker taker;

    @Column(nullable = false)
    private String content;

}
