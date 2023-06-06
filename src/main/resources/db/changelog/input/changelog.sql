-- changeset sminet:1
create table telephone(id uuid primary key, number varchar(255));
create table utilisateur(id uuid primary key, name varchar(255), telephone_id uuid);

-- changeset sminet:2
insert into telephone (id, number) values ('10000000-0000-0000-0000-000000000000', '0442000000');
insert into telephone (id, number) values ('10000000-0000-0000-0000-000000000001', '0442000001');
insert into utilisateur (id, name, telephone_id) values ('20000000-0000-0000-0000-000000000000', 'alice', '10000000-0000-0000-0000-000000000000');
insert into utilisateur (id, name, telephone_id) values ('20000000-0000-0000-0000-000000000001', 'bob',   '10000000-0000-0000-0000-000000000001');
