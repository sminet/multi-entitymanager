-- changeset sminet:1
create table telephone(id uuid primary key, number varchar(255));
create table utilisateur(id uuid primary key, name varchar(255), telephone_id uuid);
