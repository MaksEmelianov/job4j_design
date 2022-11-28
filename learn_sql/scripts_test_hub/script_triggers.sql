-- TRIGGER ROW

create table products(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price(
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

-- alter table имя_таблицы disable trigger имя_триггера;

alter table products disable trigger discount_trigger;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

-- drop trigger имя_триггера on имя_таблицы;

drop trigger discount_trigger on products;

-- TRIGGER STATEMENT

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

-- NEW TRIGGERS, код выполнения задания

-- tax_plus20_state

create or replace function tax_plus20_state()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_plus20_state_trigger
    after insert on products
    referencing new table as insetred
    for each statement
    execute procedure tax_plus20_state();

-- tax_plus20_row

create or replace function tax_plus20_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_plus20_row_trigger
    before insert on products
    for each row
    execute procedure tax_plus20_row();

-- adding_to_history

create or replace function adding_to_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values (new.name, new.price, current_timestamp(0));
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger adding_to_history_row_trigger
    after insert on products
    for each row
    execute procedure adding_to_history();