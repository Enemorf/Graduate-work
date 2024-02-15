-- liquibase formatted sql

CREATE TABLE photos
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    media_Type TEXT    NOT NULL,
    file_path TEXT    NOT NULL,
    file_size BIGINT NOT NULL,
    data      BYTEA
);
CREATE TABLE users
(

    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR DEFAULT 'USER',
    photo_id   BIGINT REFERENCES photos (id)
);
CREATE TABLE ads
(
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description TEXT,
    price       INTEGER,
    title       TEXT,
    author_id   INTEGER REFERENCES users (id),
    photo_id    BIGINT REFERENCES photos (id)
);
CREATE TABLE comments
(

    id        INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at TIMESTAMP,
    text      TEXT NOT NULL,
    author_id   INTEGER REFERENCES users (id),
    ad_id     INTEGER REFERENCES ads (id)

);