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
    purcahse_date  timestamp,
    foreign key (porder_no) references porder(porder_no)
);


-- DB 추가
insert into member (member_no, member_id,member_pw) values (0, 'admin' , 1); -- 관리자
insert into mMaterials value ( 1 );	-- 카테고리
insert into mMaterials value ( 2 );
insert into mMaterials value ( 3 );
insert into mMaterials value ( 4 );
insert into mMaterials value ( 5 );
insert into mMaterials value ( 6 );

insert into dMaterials(category_no, mater_name, mater_stock, mater_price ) values ( 1, '플렛브래드',5, 2000 );	-- 빵
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '화이트브레드',5, 2100 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '오곡브레드',5, 2200 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '올리브브레드',5, 2300 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  '위트브레드',5, 2500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 1,  'No', null,  0 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  '모짜렐라',30, 500 );	-- 치즈
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  '슈레드',25, 700 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  '아메리칸치즈',18, 800 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 2,  'No', null,  0 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '새우', 5, 800 );	-- 고기
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '미트볼', 10, 700 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '베이컨', 15, 600 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '로스트치킨', 7 , 1500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  '한우', 4,  3000 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 3,  'No', null,  0 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '양파', 10, 400 );	-- 채소
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '양배추', 10, 500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '양상추', 10, 800 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '토마토', 10 , 500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  '오이', 15,  400 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 4,  'No', null,  0 );

insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '머스타드', 8, 0 );	-- 소스
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '렌치', 9, 0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '스위트칠리', 10, 0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '스위트어니언', 10 , 0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  '후추', 15,  0 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 5,  'No', null,  0 );


insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '오렌지쥬스', 100, 1500 );	-- 음료
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '제로펩시', 80, 1500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '환타오렌지', 70, 1400 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '사이다', 90 , 1400 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  '닥터페퍼', 65,  1500 );
insert into dMaterials (category_no, mater_name, mater_stock, mater_price ) values ( 6,  'No', null,  0 );

select row_number() over(order by mater_no asc) as num, mater_no, category_no, mater_name, mater_price from dMaterials where category_no = 2;
select * from member;
select * from mMaterials;
select * from dMaterials;
select * from dMaterials where Category_no = 1;
select * from purchase; 
select * from porder;

select * from porder where member_no = 1 && o_status = 0;



