package com.cabbage.grabit.domain.user;

import com.cabbage.grabit.domain.BaseTimeEntity;
import com.cabbage.grabit.domain.products.Product;
import com.cabbage.grabit.domain.products.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("GIVER")
@Table(name="GIVER")
public class Giver extends Member {

    @Column(name = "business_num", nullable = false, length = 10)
    private String businessNum;

    @Column(nullable = false)
    private String company;

    @OneToMany(mappedBy = "giver", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Builder.Default
    private final List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "giver", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private final List<Reply> replyList = new ArrayList<>();


//    public void addProduct(Product product){
//        this.products.add(product);
//    }

    @Builder
    public Giver(String name, String email, String picture, String businessNum, String company) {
        super(name, email, picture, Role.ROLE_GIVER);
        this.businessNum = businessNum;
        this.company = company;
    }


}

