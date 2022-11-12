insert into car_bodies(name) values ('Hatchback'), ('Station wagon'),
('Pickup truck'), ('Sedan'), ('Cabriolet');

insert into car_engines(name) values ('Petrol'), ('Diesel'), ('Rocket');

insert into car_transmissions(name) values ('Automatic'), ('Manual'), ('Robot');

insert into cars(name, body_id, engine_id, trans_id) values
('BMW1', 1, 1, 1), ('BMW2', 4, 2, 1), ('Dodge', 3, 2, 1), ('VAZ', 2, 1, 2);