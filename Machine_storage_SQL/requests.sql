select c.id, c.name as "Name car", cb.name as "Name body",
ce.name as "Name engine", ct.name as "Name trans"
from cars c
right join car_bodies cb
on cb.id = c.body_id
right join car_engines ce
on ce.id = c.engine_id
right join car_transmissions ct
on ct.id = c.trans_id

select cb.name
from car_bodies cb
where cb.id not in (
	select cb.id
	from car_bodies cb
	right join cars c
	on c.body_id = cb.id
);

select ce.name
from car_engines ce
where ce.id not in (
	select ce.id
	from car_engines ce
	right join cars c
	on c.engine_id = ce.id
);

select ct.name
from car_transmissions ct
where ct.id not in (
	select ct.id
	from car_transmissions ct
	right join cars c
	on c.trans_id = ct.id
);