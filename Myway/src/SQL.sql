drop database if exists myway;

create database myway;
use myway;


create table mMaterials( -- 재료 카테고리 테이블
   category_no int auto_increment primary key
);

create table dMaterials( -- 재료 상세 테이블
   category_no int,
    mater_no int auto_increment primary key,
    mater_name varchar(15),
    mater_stock int,
   mater_price int,
   foreign key (category_no) references mMaterials(category_no)
);

create table member( -- 회원가입 테이블
   member_no int auto_increment primary key,
    member_id varchar(15) not null,
    member_pw int,
    member_phone varchar(30),
    member_name varchar(10)
);

create table recommend( -- 추천게시판 테이블
   recom_no int auto_increment primary key,
    recom_title varchar(15),
    recom_view int,
    recom_content text
);

create table bcommend( -- 댓글 테이블
   bcomm_no int auto_increment primary key,
    bcomm_content varchar(50),
    member_no int,
    recom_no int,
    foreign key (member_no) references member(member_no),
    foreign key (recom_no) references recommend(recom_no)
);

create table Porder( -- 주문 테이블
   porder_no int auto_increment primary key,
   member_no int,
    bread_no int,
    drink_no int,
    veg_no int,
    che_no int,
    source_no int,
   meat_no int,
    o_status int default 0, 
    price int,
   foreign key (member_no) references member(member_no),
    foreign key (bread_no) references dMaterials(mater_no),
    foreign key (drink_no) references dMaterials(mater_no),
    foreign key (veg_no) references dMaterials(mater_no),
    foreign key (che_no) references dMaterials(mater_no),
   foreign key (source_no) references dMaterials(mater_no),
    foreign key (meat_no) references dMaterials(mater_no)
);

create table purchase( -- 결제 테이블
   purchase_no int auto_increment primary key,
    porder_no int,
    purchase_price int,
    purchase_date  timestamp,
    foreign key (porder_no) references porder(porder_no)
);

create table coupon(
	cp_no int auto_increment primary key,
	cp_name varchar(30),
	cp_price int
);

-- DB 추가
insert into member (member_no, member_id,member_pw) values (0, 'admin' , 1); -- 관리자
insert into mMaterials value ( 1 );   -- 카테고리
insert into mMaterials value ( 2 );
insert into mMaterials value ( 3 );
insert into mMaterials value ( 4 );
insert into mMaterials value ( 5 );
insert into mMaterials value ( 6 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  'No', null,  0 );      -- 빵
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1, '플렛브래드',5, 2000 );   
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '화이트브레드',5, 2100 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '오곡브레드',5, 2200 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '올리브브레드',5, 2300 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '위트브레드',5, 2500 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  'No', null,  0 );      -- 치즈
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  '모짜렐라',30, 500 );   
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  '슈레드',25, 700 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  '아메리칸치즈',18, 800 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  'No', null,  0 );       -- 고기
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '새우', 5, 800 );   
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '미트볼', 10, 700 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '베이컨', 15, 600 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '로스트치킨', 7 , 1500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '한우', 4,  3000 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  'No', null,  0 );      -- 채소
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '양파', 10, 400 );   
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '양배추', 10, 500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '양상추', 10, 800 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '토마토', 10 , 500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '오이', 15,  400 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  'No', null,  0 );      -- 소스
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '머스타드', 8, 0 );   
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '렌치', 9, 0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '스위트칠리', 10, 0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '스위트어니언', 10 , 0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '후추', 15,  0 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  'No', null,  0 );      -- 음료
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '오렌지쥬스', 100, 1500 );   
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '제로펩시', 80, 1500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '환타오렌지', 70, 1400 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '사이다', 90 , 1400 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '닥터페퍼', 65,  1500 );

select sum(purchase_price) as total_Dsales from  purchase where purchase_date like '%2023-03-03%';


-- 추천게시판
insert into recommend (recom_title, recom_content) values ( '비엘티 BEST' , '비엘티+플랫브레드+슈레드치즈+소스(스위트칠리,랜치,스위트어니언)');
insert into recommend (recom_title, recom_content) values ( '스테이크&치즈 BEST'  , '스테이크&치즈+치즈추가+허니오트빵+소스(스모크바비큐,스윗어니언)' );
insert into recommend (recom_title, recom_content) values ( '로스트치킨 BEST' , '로스트치킨+페퍼로니추가+허니오트빵+소스(핫칠리,후추)   ' );
insert into recommend (recom_title, recom_content) values ( '쉬림프 BEST' , '쉬림프+새우더블업+에그마요+빵(취향대로)+소스(스위츠칠리,랜치)' );
insert into recommend (recom_title, recom_content) values ( '비엠티 BEST' , '비엠티+슈레드치즈+에그마요+위트브레드+소스(칠리소스,어니언소스)' );

select * from member;

insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 1 , 5200 , '2023-01-02' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 2 , 4800 , '2023-01-03' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 3 , 5000 , '2023-01-04' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 9, 15, 1, 6400);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 4 , 6400 , '2023-01-05' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 5 , 4800 , '2023-01-05' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 6 , 7900 , '2023-01-06' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 7 , 5200 , '2023-01-06' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 8 , 7900 , '2023-01-07' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 8, 15, 1, 6200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 9 , 6200 , '2023-01-07' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 9, 15, 1, 6400);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 10 , 6400 , '2023-01-08' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 11 , 5000 , '2023-01-09' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 12 , 4800 , '2023-01-10' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 13 , 7900 , '2023-01-10' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 14 , 5200 , '2023-01-11' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 15 , 5200 , '2023-01-12' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 16 , 4800 , '2023-01-13' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 17 , 5000 , '2023-01-14' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 10, 15, 1, 6500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 18 , 6500 , '2023-01-15' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 19 , 8200 , '2023-01-16' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 9, 16, 1, 8100);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 20 , 8100 , '2023-01-17' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 8, 15, 1, 6200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 21 , 6200 , '2023-01-18' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 8, 14, 1, 5500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 22 , 5500 , '2023-01-19' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 5500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 23 , 5500 , '2023-01-20' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 24 , 8200 , '2023-01-20' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 10, 12, 1, 5500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 25 , 5500 , '2023-01-21' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 26 , 8200 , '2023-01-22' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 27 , 5200 , '2023-01-23' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 10, 15, 1, 6500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 28 , 6500 , '2023-01-24' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 29 , 5200 , '2023-01-24' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 30 , 7900 , '2023-01-25' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 31 , 5200 , '2023-01-26' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 32 , 8200 , '2023-01-27' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 33 , 7900 , '2023-01-28' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 8, 15, 1, 6200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 34 , 6200 , '2023-01-29' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 35 , 5200 , '2023-01-30' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 36 , 4800 , '2023-01-31' );

insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 37 , 4800 , '2023-02-01' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 38 , 4800 , '2023-02-01' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 39 , 5000 , '2023-02-02' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 9, 15, 1, 6400);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 40 , 6400 , '2023-02-03' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 41 , 8200 , '2023-02-03' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 42 , 4800 , '2023-02-04' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 43 , 7900 , '2023-02-04' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 44 , 5000 , '2023-02-05' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 10, 15, 1, 6500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 45 , 6500 , '2023-02-06' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 46 , 8200 , '2023-02-07' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 47 , 7900 , '2023-02-08' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 8, 15, 1, 6200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 48 , 6200 , '2023-02-09' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 49 , 8200 , '2023-02-10' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 50 , 5200 , '2023-02-11' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 10, 15, 1, 6500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 51 , 6500 , '2023-02-12' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 9, 15, 1, 6400);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 52 , 6400 , '2023-02-13' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 53 , 5000 , '2023-02-14' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 54 , 4800 , '2023-02-15' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 8, 16, 1, 7900);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 55 , 7900 , '2023-02-16' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 56 , 8200 , '2023-02-17' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 10, 12, 1, 5500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 57 , 5500 , '2023-02-18' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 58 , 8200 , '2023-02-19' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 59 , 5200 , '2023-02-20' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 60 , 4800 , '2023-02-21' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 61 , 5000 , '2023-02-22' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 9, 15, 1, 6400);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 62 , 6400 , '2023-02-23' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 63 , 4800 , '2023-02-24' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 5, 33, 21, 27, 10, 15, 1, 6500);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 64 , 6500 , '2023-02-25' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 65 , 8200 , '2023-02-26' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 4, 32, 20, 26, 10, 14, 1, 5000);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 66 , 5000 , '2023-02-26' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 67 , 5200 , '2023-02-27' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 2, 30, 18, 24, 8, 12, 1, 5200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 68 , 5200 , '2023-02-27' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 3, 31, 19, 25, 9, 13, 1, 4800);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 69 , 4800 , '2023-02-28' );
insert into porder (member_no, bread_no, drink_no, veg_no, source_no, che_no, meat_no, o_status, price) values (1, 6, 34, 22, 28, 10, 16, 1, 8200);
insert into purchase (porder_no, purchase_price, purchase_date ) values ( 70 , 8200 , '2023-02-28' );

insert into coupon (cp_name, cp_price) value('가입축하쿠폰',1000);
insert into coupon (cp_name, cp_price) value('생일축하쿠폰',2000);
insert into coupon (cp_name, cp_price) value('깜짝쿠폰',3000);
