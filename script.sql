create table users(
	id serial primary key,
	name varchar(255),
	description text
);
insert into users (name, description) values ('Petr', 'Fouder job4j');
insert into users (name, description) values ('Maks', 'Student');
insert into users (name, description) values ('Misha', 'Student');
select * from users;
update users set description = 'Stud' where name = 'Maks';
delete from users where name = 'Misha';
select * from users;