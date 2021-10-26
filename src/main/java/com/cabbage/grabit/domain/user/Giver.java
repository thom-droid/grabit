package com.cabbage.grabit.domain.user;

import com.cabbage.grabit.domain.products.Products;
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
public class Giver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column(name = "business_num", nullable = false, length = 10)
    private String businessNum;

    @Column(nullable = false)
    private String company;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "giver", fetch = FetchType.LAZY)
    private List<Products> products = new ArrayList<>();

    public void addProduct(Products product){
        this.products.add(product);
    }

    public void updatePicture(String picture){
        this.picture = picture;
    }

    @Builder
    public Giver(String name, String email, String picture, String businessNum, String company) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.businessNum = businessNum;
        this.company = company;
        this.role = Role.ROLE_GIVER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giver giver = (Giver) o;
        return id.equals(giver.id) &&
                name.equals(giver.name) &&
                email.equals(giver.email) &&
                Objects.equals(picture, giver.picture) &&
                businessNum.equals(giver.businessNum) &&
                company.equals(giver.company) &&
                role == giver.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, picture, businessNum, company, role);
    }

}

