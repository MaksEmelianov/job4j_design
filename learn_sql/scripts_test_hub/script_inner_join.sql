-- one - to - many

create table drivers(
    id serial primary key,
    name varchar(255)
);
create table numbers(
    id serial primary key,
    num varchar(255),
    driver_id integer references drivers(id)
);

insert into drivers (name) values ('Maks');
insert into drivers (name) values ('Petr');
insert into drivers (name) values ('Misha');
insert into drivers (name) values ('Aleksey');
insert into drivers (name) values ('Egor');
insert into drivers (name) values ('Stas');

insert into numbers (num, driver_id) values ('o123oo123', 1);
insert into numbers (num, driver_id) values ('o001oo123', 1);
insert into numbers (num, driver_id) values ('o007oo123', 1);
insert into numbers (num, driver_id) values ('ssdvsdsd', 1);
insert into numbers (num, driver_id) values ('a123aa123', 2);
insert into numbers (num, driver_id) values ('a001aa123', 2);
insert into numbers (num, driver_id) values ('a007aa123', 2);
insert into numbers (num, driver_id) values ('wgrgergrg', 2);
insert into numbers (num, driver_id) values ('b001bb01', 3);
insert into numbers (num, driver_id) values ('b002bb01', 3);
insert into numbers (num, driver_id) values ('c111cc11', 4);
insert into numbers (num, driver_id) values (null, 4);

select dr.name as "Name driver", nm.num as "Number car"
from drivers dr inner join numbers nm on dr.id = nm.driver_id;

select dr.name, nm.num
from drivers dr inner join numbers nm on dr.id = nm.driver_id
where nm.num like '%001%';

select dr.name as "Name driver", dr.id as "ID driver", nm.id as "ID number"
from drivers dr inner join numbers nm on dr.id = nm.driver_id
where nm.num is null;