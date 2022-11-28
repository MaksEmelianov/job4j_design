insert into products (name, producer, count, price) VALUES
('product_1', 'producer_1', 3, 50),
('product_2', 'producer_2', 15, 32),
('product_3', 'producer_3', 8, 115);

-- ISOLATION LEVEL READ UNCOMMITTED
set session transaction isolation level read uncommitted;

-- ISOLATION LEVEL READ COMMITTED
set session transaction isolation level read uncommitted;

-- ISOLATION LEVEL REPEATABLE READ
set session transaction isolation level repeatable read;

-- ISOLATION LEVEL SERIALIZABLE
set session transaction isolation level serializable;

-- start & check data
start transaction;
select * from products;
select sum(price) from products;

-- edit data
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

-- recheck data
select sum(price) from products;

-- rollback & check
rollback;
select sum(price) from products;

-- commit & check
select sum(price) from products;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 404, 404);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';