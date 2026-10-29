-- Base de datos: sistema_gestion_academica
-- Crear antes con: CREATE DATABASE sistema_gestion_academica;

CREATE TABLE IF NOT EXISTS estudiantes (
    id       INTEGER PRIMARY KEY,
    nombre   VARCHAR(120) NOT NULL,
    correo   VARCHAR(150) NOT NULL UNIQUE
);

CREATE INDEX IF NOT EXISTS idx_estudiantes_nombre
    ON estudiantes(nombre);

CREATE TABLE IF NOT EXISTS cursos (
    id        INTEGER PRIMARY KEY,
    nombre    VARCHAR(120) NOT NULL,
    creditos  INTEGER NOT NULL CHECK (creditos > 0)
);