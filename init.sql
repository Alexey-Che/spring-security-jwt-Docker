create table public.role_table
(
    id   serial      not null
        constraint role_table_pk
            primary key,
    name varchar(20) not null
);

create table public.user_table
(
    id       serial not null
        constraint user_table_pk
            primary key,
    login    varchar(50),
    password varchar(500),
    role_id  integer
        constraint user_table_role_table_id_fk
            references role_table
);

create table public.message_table
(
    id      serial
        constraint message_pk
            primary key,
    text    varchar,
    user_id int not null
        constraint message_user__fk
            references user_table
);

insert into role_table(name) values ('ROLE_USER');