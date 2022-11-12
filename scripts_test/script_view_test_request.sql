create table car_bodies(
    id serial primary key,
    name varchar(255)
);
create table car_engines(
    id serial primary key,
    name varchar(255)
);
create table car_transmissions(
    id serial primary key,
    name varchar(255)
);
create table cars(
    id serial primary key,
    name varchar(255),
    body_id integer references car_bodies(id),
    engine_id integer references car_engines(id),
    trans_id integer references car_transmissions(id)
);
insert into car_bodies(name) values ('Hatchback'), ('Station wagon'),
('Pickup truck'), ('Sedan'), ('Cabriolet');
insert into car_engines(name) values ('Petrol'), ('Diesel'), ('Rocket');
insert into car_transmissions(name) values ('Automatic'), ('Manual'), ('Robot');
insert into cars(name, body_id, engine_id, trans_id) values
('BMW1', 1, 1, 1), ('BMW2', 4, 2, 1), ('Dodge', 3, 2, 1), ('VAZ', 2, 1, 2);

-- Request
create view request_mix as
    select c.id, c.name as "Name car", cb.name as "Name body",
    ce.name as "Name engine", ct.name as "Name trans"
    from cars c
    right join car_bodies cb
    on cb.id = c.body_id
    right join car_engines ce
    on ce.id = c.engine_id
    right join car_transmissions ct
    on ct.id = c.trans_id
    where (c.name is not null or cb.name is not null
    or ce.name is not null or ct.name is not null) and
    c.name like '%BMW%' or ct.name = 'Manual'
    group by (c.id, c.name, cb.name,
    ce.name, ct.name)
    having count(ct.id) > 0 or count(ce.id) > 1

select c.name as "Name car", ce.name as "Name engine"
from request_mix
