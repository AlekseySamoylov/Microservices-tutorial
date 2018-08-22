CREATE ROLE db_user;
ALTER ROLE db_user WITH SUPERUSER
INHERIT
CREATEROLE
CREATEDB
LOGIN
NOREPLICATION
NOBYPASSRLS
PASSWORD 'md5d7980ace5b59ea073a4c25bd767b9c8d';

CREATE DATABASE public_db WITH TEMPLATE = template0 OWNER = postgres;
GRANT ALL ON DATABASE public_db TO db_user;

\connect public_db;

CREATE TABLE social_user(
  id    serial primary key not null,
  username  varchar(255) unique not null,
  password  varchar(255)
);

INSERT INTO social_user VALUES (default, 'alex', '123');
