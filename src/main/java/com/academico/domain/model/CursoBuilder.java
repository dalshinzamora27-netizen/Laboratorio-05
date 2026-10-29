package com.academico.domain.model;

/**
 * PATRÓN BUILDER: construye un Curso paso a paso y valida
 * sus datos antes de entregarlo.
 */
public class CursoBuilder {

    private int id;
    private String nombre;
    private int creditos;

    public CursoBuilder conId(int id) {
        this.id = id;
        return this;
    }

    public CursoBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public CursoBuilder conCreditos(int creditos) {
        this.creditos = creditos;
        return this;
    }

    public Curso build() {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor que 0.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser mayores que 0.");
        }
        return new Curso(id, nombre.trim(), creditos);
    }
}