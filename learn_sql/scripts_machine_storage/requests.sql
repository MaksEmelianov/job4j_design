select c.id, c.name as "Name car", cb.name as "Name body",
ce.name as "Name engine", ct.name as "Name trans"
from cars c
left join car_bodies cb
on c.body_id = cb.id
left join car_engines ce
on c.engine_id = ce.id
left join car_transmissions ct
on c.trans_id = ct.id;

select cb.name
from car_bodies cb
left join cars c
on cb.id = c.body_id
where c.body_id is null;

select ce.name
from car_engines ce
left join cars c
on ce.id = c.engine_id
where c.engine_id is null;

select ct.name
from car_transmissions ct
left join cars c
on ct.id = c.engine_id
where c.engine_id is null;