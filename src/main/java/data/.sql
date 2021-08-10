CREATE DATABASE "TV2"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Danish_Denmark.1252'
    LC_CTYPE = 'Danish_Denmark.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE producenter
(
    id   SERIAL PRIMARY KEY,
    navn VARCHAR(80) NOT NULL
);

CREATE TABLE programmer
(
    id           SERIAL PRIMARY KEY,
    navn         VARCHAR(120) NOT NULL,
    producent_id INTEGER      NOT NULL REFERENCES producenter (id)
);

CREATE TABLE personer
(
    id           SERIAL PRIMARY KEY,
    fornavn      VARCHAR(50) NOT NULL,
    efternavn    VARCHAR(50) NOT NULL,
    nationalitet VARCHAR(50) NOT NULL,
    dag          INTEGER     NOT NULL,
    maaned       INTEGER     NOT NULL,
    aar          INTEGER     NOT NULL
);

CREATE TABLE roller
(
    id        SERIAL PRIMARY KEY,
    navn      VARCHAR(100) NOT NULL,
    type      VARCHAR(50)  NOT NULL,
    person_id INTEGER REFERENCES personer (id)
);

CREATE TABLE program_rolle
(
    program_id INTEGER NOT NULL REFERENCES programmer (id),
    rolle_id   INTEGER NOT NULL REFERENCES roller (id)
);
