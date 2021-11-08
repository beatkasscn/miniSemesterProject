use cat_platform;

create table buyer(
    buyer_id int auto_increment,
    buyer_username varchar(20),
    buyer_realname varchar(20),
    buyer_password varchar(20),
    buyer_Email varchar(20) unicode,
    buyer_imageSrc varchar(100),
    buyer_tel varchar(11) unicode,
    buyer_address varchar(100),
    buyer_IDnumber varchar(18) unicode,
    primary key (buyer_id)
);

create table seller(
    seller_id int auto_increment,
    seller_username varchar(20),
    seller_realname varchar(20),
    seller_password varchar(20),
    seller_Email varchar(20) unicode,
    seller_imageSrc varchar(100),
    seller_tel varchar(11) unicode,
    seller_address varchar(100),
    seller_IDnumber varchar(18) unicode,
    seller_storeid varchar(8) unicode,
    primary key (seller_id),
    constraint check_storeid check ( length(seller_storeid) = 8 )
);
create table admin(
    admin_id int auto_increment,
    admin_username varchar(20),
    admin_password varchar(20),
    primary key (admin_id)
);
create table store(
    store_id varchar(8),
    store_name varchar(10),
    primary key (store_id),
    foreign key (store_id) references seller(seller_storeid)
);
create table cat(
    cat_storeid varchar(8),
    cat_name varchar(10),
    cat_src varchar(100),
    cat_price float default 100,
    primary key (cat_storeid,cat_name),
    foreign key (cat_storeid) references store(store_id),
    check ( cat_price > 0 )
);
create table trade(
    trade_id bigint auto_increment,
    trade_date datetime ,
    trade_buyer_id int,
    trade_store int,
    trade_cat_storeid varchar(8),
    trade_cat_name varchar(10),
    trade_price float,
    primary key (trade_id),
    foreign key (trade_buyer_id) references buyer(buyer_id),
    foreign key (trade_store) references store(store_id),
    foreign key (trade_cat_name) references cat(cat_name)
);

