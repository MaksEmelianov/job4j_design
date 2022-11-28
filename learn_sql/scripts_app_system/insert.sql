insert into roles(name) values ('Admin');
insert into roles(name) values ('Assistant');
insert into roles(name) values ('User');

insert into rights(name) values ('Full access');
insert into rights(name) values ('Limited access');
insert into rights(name) values ('Easy access');
insert into rights(name) values ('Banned');

insert into roles_rights(role_id, right_id) values (1, 1);
insert into roles_rights(role_id, right_id) values (2, 2);
insert into roles_rights(role_id, right_id) values (3, 3);

insert into users(name, role_id) values ('Petr', 1);
insert into users(name, role_id) values ('Mask', 2);
insert into users(name, role_id) values ('Egor', 3);

insert into statuses(name) values ('Started');
insert into statuses(name) values ('Stoped');
insert into statuses(name) values ('Cheked');
insert into statuses(name) values ('Banned');

insert into categories(name) values ('Important');
insert into categories(name) values ('News');
insert into categories(name) values ('Items');

insert into items(name, text, user_id, categ_id, status_id)
values ('Terms of Use', 'Terms of Use...', 1, 1, 1);

insert into items(name, text, user_id, categ_id, status_id)
values ('News #1', 'News #1...', 2, 2, 1);

insert into items(name, text, user_id, categ_id, status_id)
values ('test', 'test', 3, 3, 2);

insert into items(name, text, user_id, categ_id, status_id)
values ('Item1', 'Item1', 3, 3, 3);

insert into items(name, text, user_id, categ_id, status_id)
values ('Item2', 'Item2', 3, 3, 1);

insert into comments(text, item_id) values ('Text1', 1);
insert into comments(text, item_id) values ('Text2', 2);
insert into comments(text, item_id) values ('Text3', 5);

insert into files(name, item_id) values ('File1', 1);
insert into files(name, item_id) values ('File2', 2);
insert into files(name, item_id) values ('File3', 5);