-- MYSQL CODE
create database trans_test;
use trans_test;

create table 'products' (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
describe products;

--delete from products
--truncate products;