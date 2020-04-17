create table payment (
   id bigint(20) not null auto_increment comment "ID",
   serial varchar(200),
   primary key (id)
) engine=innodb auto_increment=1 default charset=utf8;


curl -X POST --header "Content-Type: application/json" -d '{"serial": "hehe"}' 127.0.0.1:8001/payment