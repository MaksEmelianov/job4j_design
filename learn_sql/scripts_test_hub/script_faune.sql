create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Wolf', 15, '1720-10-01');
insert into fauna(name, avg_age, discovery_date) values ('Deer', 12, '1970-10-01');
insert into fauna(name, avg_age, discovery_date) values ('Hare', 4, '1920-10-01');
insert into fauna(name, avg_age, discovery_date) values ('Raccoon', 7, null);
insert into fauna(name, avg_age, discovery_date) values ('Kangaroo', 15, '1820-07-01');
insert into fauna(name, avg_age, discovery_date) values ('Swamp fish blue', 17000, '1820-07-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';