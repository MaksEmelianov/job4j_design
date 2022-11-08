create table users2(
	id serial primary key,
	name varchar(255),
	age integer,
	text text
);
insert into users2 (name, age, text) values ('Petr', '43', 'Fouder job4j');
insert into users2 (name, age, text) values ('Maks', 25, 'Student');
insert into users2 (name, age, text) values ('Misha', 10, 'Student');
select * from users2;
update users2 set age = 30 where name = 'Maks';
delete from users2 where name = 'Misha';
select * from users2;