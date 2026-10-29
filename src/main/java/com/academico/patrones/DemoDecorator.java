package com.academico.patrones;

import com.academico.domain.repository.CursoRepository;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryPostgres;
import com.academico.infrastructure.decorator.CursoRepositoryConLog;

public class DemoDecorator {

    public static void main(String[] args) {
        // Objeto original y el mismo objeto "envuelto" con el decorador.
        CursoRepository original = new CursoRepositoryPostgres();
        CursoRepository decorado = new CursoRepositoryConLog(original);

        System.out.println("=== Sin decorador (no registra nada) ===");
        System.out.println("Cursos en la base: " + original.listar().size());

        System.out.println("\n=== Con decorador (registra cada operación) ===");
        decorado.listar();
        decorado.buscarPorId(1);
        decorado.buscarPorId(999);
    }
}