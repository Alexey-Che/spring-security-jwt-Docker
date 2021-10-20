create table spring_course.role_table
(
    id   serial      not null
        constraint role_table_pk
            primary key,
    name varchar(20) not null
);


create table spring_course.user_table
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

create table spring_course.message
(
    id      serial
        constraint message_pk
            primary key,
    text    varchar,
    user_id int not null
        constraint message_user__fk
            references user_table
);
-- CREATE TABLE IF NOT EXISTS role_table(
--     id int NOT NULL PRIMARY KEY ,
--     name TEXT
-- );
--
--
-- CREATE TABLE IF NOT EXISTS user_table(
--     id int NOT NULL PRIMARY KEY,
--     login TEXT,
--     password TEXT,
--     role_id int,
--     FOREIGN KEY (role_id) REFERENCES role_table(id)
-- );
--
-- CREATE TABLE IF NOT EXISTS message_table(
--     id int NOT NULL PRIMARY KEY,
--     text TEXT,
--     user_id int NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES user_table(id)
-- );