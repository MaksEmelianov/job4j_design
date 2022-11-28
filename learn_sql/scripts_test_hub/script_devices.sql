create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Apple', 10000), ('Samsung', 9000), ('HTC', 948);
insert into people(name) values ('Petr'), ('Maks'), ('Egor'), ('Aleksey');
insert into devices_people(device_id, people_id) values (1,1), (1,2), (2,3), (3,4), (2,1);

select avg(d.price) as "Adv Price device"
from devices d;

select p.name, avg(d.price) as "Adv Price device"
from devices_people dp
inner join devices d
on dp.device_id = d.id
inner join people p
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) as "Adv Price device"
from devices_people dp
inner join devices d
on dp.device_id = d.id
inner join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;
