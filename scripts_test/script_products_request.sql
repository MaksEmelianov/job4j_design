
select p.name
from product p
inner join type t
on p.type_id = t.id
where t.name like '%СЫР%';

select p.name
from product p
where p.name like '%мороженое%';

select p.name, p.expired_date
from product p
where current_date > expired_date;

--select p.name, t.name, p.price
--from product p
--inner join type t
--on p.type_id = t.id
--group by t.name

select p.name, p.price
from product p
where p.price = (select max(p.price) from product p);

select t.name, count(p.id) as "Count prod. in type"
from type t
inner join product p
on t.id = p.type_id
group by t.name;

select p.name
from product p
inner join type t
on p.type_id = t.id
where t.name like '%СЫР%' or t.name like '%МОЛОКО%';

select t.name
from type t
inner join product p
on t.id = p.type_id
group by t.name
having count(p.id) < 10;

select p.name, t.name
from product p
inner join type t
on p.type_id = t.id