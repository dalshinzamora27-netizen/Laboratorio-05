package com.academico.patrones;

import com.academico.domain.model.Curso;
import com.academico.domain.model.CursoBuilder;

public class DemoBuilder {

    public static void main(String[] args) {
        System.out.println("=== Curso válido ===");
        Curso curso = new CursoBuilder()
                .conId(30)
                .conNombre("  Base de Datos  ")
                .conCreditos(4)
                .build();
        System.out.println("Creado: id=" + curso.getId()
                + ", nombre='" + curso.getNombre()
                + "', créditos=" + curso.getCreditos());

        System.out.println("\n=== Curso inválido (sin nombre) ===");
        try {
            new CursoBuilder().conId(31).conCreditos(3).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazado: " + e.getMessage());
        }

        System.out.println("\n=== Curso inválido (créditos negativos) ===");
        try {
            new CursoBuilder().conId(32).conNombre("Redes").conCreditos(-2).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazado: " + e.getMessage());
        }
    }
}