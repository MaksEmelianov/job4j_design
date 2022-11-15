create table departments(
    id serial primary key,
    name varchar(255)
);
create table employees(
    id serial primary key,
    name varchar(255),
    dep_id integer references departments(id)
);
create table teens(
    id serial primary key,
    name varchar(255),
    gender char(1)
);
-- gender: 1 - man, 0 - woman

insert into departments(name) values
('Marketing'), ('Development'), ('Testing'), ('Management');

insert into employees(name, dep_id) values
('Petr', 4), ('Maks', 2), ('Egor', 1), ('Stats', 1);

insert into teens(name, gender) values
('Anastasiya', 'W'), ('Alisa', 'W'), ('Andrey', 'M'), ('Maria', 'W');

select d.name
from departments d
left join employees e
on d.id = e.dep_id
where e.dep_id is null
group by d.name

select d.name, count(e.id)
from employees e
right join departments d
on e.dep_id = d.id
group by d.name;

select d.name, count(e.id)
from departments d
left join employees e
on d.id = e.dep_id
group by d.name;

select t1.name Name1, t2.name Name2, concat(t1.gender, ' + ', t2.gender)
from teens t1
cross join teens t2
where t1.name != t2.name and t1.gender != t2.gender
