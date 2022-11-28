-- insert data
call insert_data('product_01', 'producer_01', 10, 15);
call insert_data('product_02', 'producer_02', 5, 25);
call insert_data('product_03', 'producer_03', 15, 5);
call insert_data('product_04', 'producer_04', 0, 5);

-- delete data procedure
create or replace procedure delete_data(d_id integer)
language 'plpgsql' as $$
    begin
        delete from products p where id = d_id and count = 0;
    end;
$$;

call delete_data(1);

-- delete data function
create or replace function f_delete_data(d_id integer)
returns void
language 'plpgsql' as $$
    begin
        delete from products p where id = d_id and count = 0;
    end;
$$;

select f_delete_data(4);