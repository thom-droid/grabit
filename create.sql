
    create table giver (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        business_num varchar(10) not null,
        company varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table product (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        category varchar(255) not null,
        details varchar(255) not null,
        name varchar(255) not null,
        price integer not null,
        rate decimal(2,1),
        review_count decimal(7,0),
        sales_status bit not null,
        subscription_count decimal(7,0),
        giver_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table product_regions (
       product_id bigint not null,
        regions_id bigint not null
    ) engine=InnoDB

    create table region (
       id bigint not null auto_increment,
        gugun varchar(255) not null,
        sido varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table region_products (
       region_id bigint not null,
        products_id bigint not null
    ) engine=InnoDB

    create table taker (
       id bigint not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table product 
       add constraint FKr5xi4wp8iia4evm3kddr04one 
       foreign key (giver_id) 
       references giver (id)

    alter table product_regions 
       add constraint FKt2g8vuley9y9kg0kngoka57fe 
       foreign key (regions_id) 
       references region (id)

    alter table product_regions 
       add constraint FK6ebw84ybrq5kfwr6rque2epsh 
       foreign key (product_id) 
       references product (id)

    alter table region_products 
       add constraint FKpefgdi5d141xj4j0vb4jjf6gi 
       foreign key (products_id) 
       references product (id)

    alter table region_products 
       add constraint FKkgdijuimpl0sqbmf75j885sl1 
       foreign key (region_id) 
       references region (id)

    create table giver (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        business_num varchar(10) not null,
        company varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table product (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        category varchar(255) not null,
        details varchar(255) not null,
        name varchar(255) not null,
        price integer not null,
        rate decimal(2,1),
        review_count decimal(7,0),
        sales_status bit not null,
        subscription_count decimal(7,0),
        giver_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table product_regions (
       product_id bigint not null,
        regions_id bigint not null
    ) engine=InnoDB

    create table region (
       id bigint not null auto_increment,
        gugun varchar(255) not null,
        sido varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table region_products (
       region_id bigint not null,
        products_id bigint not null
    ) engine=InnoDB

    create table taker (
       id bigint not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table product 
       add constraint FKr5xi4wp8iia4evm3kddr04one 
       foreign key (giver_id) 
       references giver (id)

    alter table product_regions 
       add constraint FKt2g8vuley9y9kg0kngoka57fe 
       foreign key (regions_id) 
       references region (id)

    alter table product_regions 
       add constraint FK6ebw84ybrq5kfwr6rque2epsh 
       foreign key (product_id) 
       references product (id)

    alter table region_products 
       add constraint FKpefgdi5d141xj4j0vb4jjf6gi 
       foreign key (products_id) 
       references product (id)

    alter table region_products 
       add constraint FKkgdijuimpl0sqbmf75j885sl1 
       foreign key (region_id) 
       references region (id)

    create table giver (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        business_num varchar(10) not null,
        company varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table product (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        category varchar(255) not null,
        details varchar(255) not null,
        name varchar(255) not null,
        price integer not null,
        rate decimal(2,1),
        review_count decimal(7,0),
        sales_status bit not null,
        subscription_count decimal(7,0),
        giver_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table product_regions (
       product_id bigint not null,
        regions_id bigint not null
    ) engine=InnoDB

    create table region (
       id bigint not null auto_increment,
        gugun varchar(255) not null,
        sido varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table region_products (
       region_id bigint not null,
        products_id bigint not null
    ) engine=InnoDB

    create table taker (
       id bigint not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table product 
       add constraint FKr5xi4wp8iia4evm3kddr04one 
       foreign key (giver_id) 
       references giver (id)

    alter table product_regions 
       add constraint FKt2g8vuley9y9kg0kngoka57fe 
       foreign key (regions_id) 
       references region (id)

    alter table product_regions 
       add constraint FK6ebw84ybrq5kfwr6rque2epsh 
       foreign key (product_id) 
       references product (id)

    alter table region_products 
       add constraint FKpefgdi5d141xj4j0vb4jjf6gi 
       foreign key (products_id) 
       references product (id)

    alter table region_products 
       add constraint FKkgdijuimpl0sqbmf75j885sl1 
       foreign key (region_id) 
       references region (id)

    create table giver (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        business_num varchar(10) not null,
        company varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table product (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        category varchar(255) not null,
        details varchar(255) not null,
        name varchar(255) not null,
        price integer not null,
        rate decimal(2,1),
        review_count decimal(7,0),
        sales_status bit not null,
        subscription_count decimal(7,0),
        giver_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table product_regions (
       product_id bigint not null,
        regions_id bigint not null
    ) engine=InnoDB

    create table region (
       id bigint not null auto_increment,
        gugun varchar(255) not null,
        sido varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table region_products (
       region_id bigint not null,
        products_id bigint not null
    ) engine=InnoDB

    create table taker (
       id bigint not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table product 
       add constraint FKr5xi4wp8iia4evm3kddr04one 
       foreign key (giver_id) 
       references giver (id)

    alter table product_regions 
       add constraint FKt2g8vuley9y9kg0kngoka57fe 
       foreign key (regions_id) 
       references region (id)

    alter table product_regions 
       add constraint FK6ebw84ybrq5kfwr6rque2epsh 
       foreign key (product_id) 
       references product (id)

    alter table region_products 
       add constraint FKpefgdi5d141xj4j0vb4jjf6gi 
       foreign key (products_id) 
       references product (id)

    alter table region_products 
       add constraint FKkgdijuimpl0sqbmf75j885sl1 
       foreign key (region_id) 
       references region (id)

    create table giver (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        business_num varchar(10) not null,
        company varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table product (
       id bigint not null auto_increment,
        created_date datetime,
        modified_date datetime,
        category varchar(255) not null,
        details varchar(255) not null,
        name varchar(255) not null,
        price integer not null,
        rate decimal(2,1),
        review_count decimal(7,0),
        sales_status bit not null,
        subscription_count decimal(7,0),
        giver_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table product_regions (
       product_id bigint not null,
        regions_id bigint not null
    ) engine=InnoDB

    create table region (
       id bigint not null auto_increment,
        gugun varchar(255) not null,
        sido varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table region_products (
       region_id bigint not null,
        products_id bigint not null
    ) engine=InnoDB

    create table taker (
       id bigint not null auto_increment,
        email varchar(255) not null,
        name varchar(255) not null,
        picture varchar(255),
        role varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    alter table product 
       add constraint FKr5xi4wp8iia4evm3kddr04one 
       foreign key (giver_id) 
       references giver (id)

    alter table product_regions 
       add constraint FKt2g8vuley9y9kg0kngoka57fe 
       foreign key (regions_id) 
       references region (id)

    alter table product_regions 
       add constraint FK6ebw84ybrq5kfwr6rque2epsh 
       foreign key (product_id) 
       references product (id)

    alter table region_products 
       add constraint FKpefgdi5d141xj4j0vb4jjf6gi 
       foreign key (products_id) 
       references product (id)

    alter table region_products 
       add constraint FKkgdijuimpl0sqbmf75j885sl1 
       foreign key (region_id) 
       references region (id)
