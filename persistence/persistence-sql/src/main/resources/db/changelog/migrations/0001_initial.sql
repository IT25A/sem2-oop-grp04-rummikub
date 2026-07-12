--liquibase formatted sql

--changeset system:1 dbms:postgresql
CREATE TABLE IF NOT EXISTS "rummikub_games"
 (
     id     UUID PRIMARY KEY,
     game   JSONB NOT NULL
 );
