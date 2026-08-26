DROP DATABASE IF EXISTS testdb; 
CREATE DATABASE testdb;
USE testdb;
CREATE TABLE list( 
    no int AUTO_INCREMENT , 
    pn VARCHAR(255) ,
    num int not null,
    constraint PRIMARY KEY( no ) 
);
insert into list( pn, num )values( "010-1111-1111", 1 ),( "010-2222-2222", 3); -- 샘플 데이터 2개 