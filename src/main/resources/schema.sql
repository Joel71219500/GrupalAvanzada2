-- Script para crear la tabla author con ID autoincremental
DROP TABLE IF EXISTS author;

CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,  -- BIGSERIAL es autoincremental en PostgreSQL
    name VARCHAR(255) NOT NULL,
    version INTEGER NOT NULL
);

