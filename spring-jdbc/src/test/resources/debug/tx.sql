create database tx_manager;
create table user(
id bigint auto_increment primary key comment '用户唯一id',
name varchar(20) not null comment '用户名称',
age int not null comment '用户年龄信息'
)engine=innodb default charset=utf8mb4;

insert into user(id,name,age) values (1, 'carl',25);
insert into user(id,name,age) values (2, 'spring',25);

create table account(
id bigint auto_increment primary key comment '账户的唯一id信息',
user_id bigint not null comment '用户的id新',
account_balance int not null default 0 comment '用户的账户余额,单位为分,默认为0'
) engine=innodb default charset=utf8mb4;

insert into account (id,user_id,account_balance) values (1,1,10000);
insert into account (id,user_id,account_balance) values (2,2,40000);

create table consume_record(
id bigint auto_increment primary key comment '转账记录的唯一标识',
origin_user_id bigint not null comment '付款人唯一标识',
target_user_id bigint not null comment '收款人唯一标识',
consume_reason varchar(128) default null comment '消费记录信息',
amount int not null comment '转账金额'
)engine=innodb default charset=utf8mb4;

