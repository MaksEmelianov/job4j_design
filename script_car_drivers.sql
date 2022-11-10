create table cars(
    id serial primary key,
    name varchar(255),
    number varchar(255)
);

create table drivers(
    id serial primary key,
    name varchar(255),
    number_pass integer
);

create table drivers_cars(
    id serial primary key,
    car_id references cars(id),
    driver_is references drivers(id)
);

insert into cars(name) values ('VAZ 2101');
insert into cars(name) values ('VAZ 2111');
insert into cars(name) values ('VAZ 3104');

insert into drivers(name) values ('Petr');
insert into drivers(name) values ('Maks');
insert into drivers(name) values ('Egor');

insert into drivers_cars(car_id, driver_id) values (1,1);
insert into drivers_cars(car_id, driver_id) values (1,2);
insert into drivers_cars(car_id, driver_id) values (1,3);
insert into drivers_cars(car_id, driver_id) values (2,2);
insert into drivers_cars(car_id, driver_id) values (2,3);
insert into drivers_cars(car_id, driver_id) values (3,1);