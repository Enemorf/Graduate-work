--liquibase formatted sql

--changeset Enerlo: create tables
create table images(
    id serial primary key,
    path varchar(200),
    size bigint,
    content_type varchar(200)
);
create table users(
    id serial primary key,
    username varchar(50),
    first_name varchar(50),
    last_name varchar(50),
    password varchar(200),
    phone varchar(50),
    role varchar(50),
    image int,
    email varchar(200),
    foreign key (image) references images (id)
);
create table ads(
    id serial primary key,
    price integer,
    title text,
    description text,
    author int,
    image int,
    foreign key (author) references users (id),
    foreign key (image) references images(id)
);
create table comments(
     pk serial primary key,
     created_at date,
     text text,
     author int,
     ad int,
     foreign key (author) references users (id),
     foreign key (ad) references ads (id)
);