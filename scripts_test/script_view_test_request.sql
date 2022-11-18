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
    left join car_bodies cb
    on c.body_id = cb.id
    left join car_engines ce
    on c.engine_id = ce.id
    right join car_transmissions ct
    on c.trans_id = ct.id
    where (c.name is not null or cb.name is not null
    or ce.name is not null or ct.name is not null) and
    c.name like '%BMW%' or ct.name = 'Manual';

select "Name car", "Name engine"
from request_mix
