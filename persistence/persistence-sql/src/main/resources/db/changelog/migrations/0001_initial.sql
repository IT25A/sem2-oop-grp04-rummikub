--liquibase formatted SQL

--changeset system:1 dbms:postgresql
CREATE TABLE IF NOT EXISTS "game_states"
(
 	 "id"       TEXT PRIMARY KEY,
 	 "game"     JSONB NOT NULL
);
--rollback DROP TABLE game_states;