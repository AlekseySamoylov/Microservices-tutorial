CREATE ROLE auth_user;
ALTER ROLE auth_user WITH SUPERUSER
INHERIT
CREATEROLE
CREATEDB
LOGIN
NOREPLICATION
NOBYPASSRLS
PASSWORD 'md5d7980ace5b59ea073a4c25bd767b9c8d';

CREATE DATABASE oauth_db WITH TEMPLATE = template0 OWNER = postgres;
GRANT ALL ON DATABASE jooq_db TO auth_user;