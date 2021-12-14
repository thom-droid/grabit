package com.cabbage.grabit.domain.user;

import com.cabbage.grabit.domain.product.Product;
import com.cabbage.grabit.domain.reply.Reply;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("GIVER")
@Table(name="GIVER")
@Entity
public class Giver extends Member {

    @Column(name = "business_num", nullable = false, length = 10)
    private String businessNum;

    @Column(nullable = false)
    private String company;

    @JsonIgnore
    @OneToMany(mappedBy = "giver", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final List<Product> productList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "giver", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<Reply> replyList = new ArrayList<>();

    @Builder
    public Giver(String name, String email, String picture, String businessNum, String company) {
        super(name, email, picture, Role.ROLE_GIVER);
        this.businessNum = businessNum;
        this.company = company;
    }
}

