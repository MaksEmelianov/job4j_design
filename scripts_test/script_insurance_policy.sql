-- one - to - one

create table policy_number(
    id serial primary key,
    number integer
);

create table users(
    id serial primary key,
    number_pass integer,
    name varchar(255)
);

create table users_policy(
    id serial primary key,
    policy_id integer references policy_number(id) unique,
    user_id integer references users(id) unique
);

insert into policy_number (number) values (123312312);
insert into policy_number (number) values (756757756);
insert into policy_number (number) values (854385934);

insert into users (number_pass, name) values (234324, 'Petr');
insert into users (number_pass, name) values (345354, 'Maks');
insert into users (number_pass, name) values (876768, 'Stas');

insert into users_policy (policy_id, user_id) values (1,3);
insert into users_policy (policy_id, user_id) values (2,1);
insert into users_policy (policy_id, user_id) values (3,2);