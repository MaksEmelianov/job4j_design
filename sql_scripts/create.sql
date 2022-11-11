create table roles(
    id serial primary key,
    name varchar(255)
);

create table rights(
    id serial primary key,
    name varchar(255)
);

create table statuses(
    id serial primary key,
    name varchar(255)
);

create table categories(
    id serial primary key,
    name varchar(255)
);

create table roles_rights(
    id serial primary key,
    role_id integer references roles(id),
    right_id integer references rights(id)
);

create table users(
    id serial primary key,
    name varchar,
    role_id integer references roles(id)
);

create table items(
    id serial primary key,
    name varchar(255),
    text text,
    user_id integer references users(id),
    categ_id integer references categories(id),
    status_id integer references statuses(id)
);

create table comments(
    id serial primary key,
    text text,
    item_id integer references items(id)
);

create table files(
    id serial primary key,
    name varchar(255),
    item_id integer references item(id)
);