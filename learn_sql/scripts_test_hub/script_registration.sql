-- many - to - one

create table place_of_residence(
    id serial primary key,
    number_home integer,
    number_flat integer
);

create table residents(
    id serial primary key,
    name varchar(255),
    number_pass integer,
    place_id integer references place_of_residence(id)
);

insert into place_of_residence (number_home, number_flat) values (1,1);
insert into place_of_residence (number_home, number_flat) values (1,2);

insert into residents (name, number_pass, place_id) values ('Petr', 42342342, 1);
insert into residents (name, number_pass, place_id) values ('Maks', 45345354, 1);
insert into residents (name, number_pass, place_id) values ('Stas', 456756765, 1);
insert into residents (name, number_pass, place_id) values ('Egor', 878568686, 2);