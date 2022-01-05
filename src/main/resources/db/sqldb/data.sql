insert into Member(name, email, picture, role)
values ('할명수','audtn@gmail.com','default.jpg', 'ROLE_GIVER' );
insert into Member(name, email, picture, role)
values ('정주나','wnsk@gmail.com','default.jpg', 'ROLE_GIVER' );

insert into GIVER (id, business_num, company)
values (1, '1234567890','명수네반찬');
insert into GIVER (id, business_num, company)
values (2, '1234567809','주나네포차');

insert into Region (sido, gugun)
values ('서울','종로구');
insert into Region (sido, gugun)
values ('서울','중구');
insert into Region (sido, gugun)
values ('서울','용산구');


insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (1,'CLOTHING_SOCKS','12가지인생의법칙',10000,'혼돈의해독제랍니다', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (2,'CLOTHING','라쇼몽',7500,'아쿠타가와류노스께의대표작', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (1,'CLOTHING','수학의정석',13500,'사서한번도보지않은몇안되는책', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (2,'CLOTHING_STYLING','잉여인간',8500,'솔직히재미없었다', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (1,'FOOD','변신',6500,'카프카이즘의대표작', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (2,'FOOD_MEAL_KIT','벨아미',13500,'읽어봐야하는모파상의대표작', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (1,'LIVING','도롱뇽과의전쟁',9500,'도롱뇽을통해인간을관찰한다', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (2,'LIVING_WASHING','칼의노래',9500,'이순신장군소설', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (1,'LIVING_DAILY_SUPPLIES','허클베리핀의모험',5500,'톰소여의모험후속작', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (2,'CLOTHING','레미제라블',10000,'눈물없인볼수없는대서사시', 'Y', current_timestamp, 'default.jpg');
insert into Product (giver_id, category, name, price, details, sale_status, created_date, image)
values (1,'ETC','돈키호테',10000,'모두가미쳤다고해도', 'Y', current_timestamp, 'default.jpg');

insert into product_region (product_id, region_id)
values (1,1);
insert into product_region (product_id, region_id)
values (1,2);
insert into product_region (product_id, region_id)
values (2,1);
insert into product_region (product_id, region_id)
values (2,3);
insert into product_region (product_id, region_id)
values (3,1);
insert into product_region (product_id, region_id)
values (3,2);
insert into product_region (product_id, region_id)
values (4,3);
insert into product_region (product_id, region_id)
values (5,1);
insert into product_region (product_id, region_id)
values (6,2);
insert into product_region (product_id, region_id)
values (6,3);
insert into product_region (product_id, region_id)
values (7,1);
insert into product_region (product_id, region_id)
values (8,2);
insert into product_region (product_id, region_id)
values (9,1);
insert into product_region (product_id, region_id)
values (10,2);


