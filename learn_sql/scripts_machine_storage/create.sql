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