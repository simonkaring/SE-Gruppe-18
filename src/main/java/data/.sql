CREATE DATABASE "TV2"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE producers
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL
);

-- Every production has a producer
CREATE TABLE productions
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(120) NOT NULL,
    producer_id  INTEGER      NOT NULL REFERENCES producers (id)
);

CREATE TABLE person
(
    id           SERIAL PRIMARY KEY,
    firstname    VARCHAR(50) NOT NULL,
    lastname     VARCHAR(50) NOT NULL,
    nationality  VARCHAR(50) NOT NULL,
    day          INTEGER     NOT NULL,
    month        INTEGER     NOT NULL,
    year         INTEGER     NOT NULL
);

CREATE TABLE roles
(
    id        SERIAL PRIMARY KEY,
    navn      VARCHAR(100) NOT NULL,
    type      VARCHAR(50)  NOT NULL,
    person_id INTEGER REFERENCES person (id)
);

CREATE TABLE production_role
(
    program_id INTEGER NOT NULL REFERENCES productions (id),
    rolle_id   INTEGER NOT NULL REFERENCES roles (id)
);

------------------------------------------------------------------

-- CREATE DATABASE "TV2"
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;
--
-- CREATE TABLE producenter
-- (
--     id   SERIAL PRIMARY KEY,
--     navn VARCHAR(80) NOT NULL
-- );
--
-- CREATE TABLE programmer
-- (
--     id           SERIAL PRIMARY KEY,
--     navn         VARCHAR(120) NOT NULL,
--     producent_id INTEGER      NOT NULL REFERENCES producers (id)
-- );
--
-- CREATE TABLE personer
-- (
--     id           SERIAL PRIMARY KEY,
--     fornavn      VARCHAR(50) NOT NULL,
--     efternavn    VARCHAR(50) NOT NULL,
--     nationalitet VARCHAR(50) NOT NULL,
--     dag          INTEGER     NOT NULL,
--     maaned       INTEGER     NOT NULL,
--     aar          INTEGER     NOT NULL
-- );
--
-- CREATE TABLE roller
-- (
--     id        SERIAL PRIMARY KEY,
--     navn      VARCHAR(100) NOT NULL,
--     type      VARCHAR(50)  NOT NULL,
--     person_id INTEGER REFERENCES person (id)
-- );
--
-- CREATE TABLE program_rolle
-- (
--     program_id INTEGER NOT NULL REFERENCES productions (id),
--     rolle_id   INTEGER NOT NULL REFERENCES roles (id)
-- );
