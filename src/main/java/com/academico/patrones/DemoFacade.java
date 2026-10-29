package com.academico.patrones;

import com.academico.application.facade.SistemaAcademicoFacade;
import com.academico.application.service.CursoService;
import com.academico.application.service.EstudianteService;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryPostgres;
import com.academico.infrastructure.adapter.out.persistence.EstudianteRepositoryPostgres;
import com.academico.infrastructure.observer.AuditoriaCursoObserver;

public class DemoFacade {

    public static void main(String[] args) {
        CursoService cursoService = new CursoService(new CursoRepositoryPostgres());
        EstudianteService estudianteService =
                new EstudianteService(new EstudianteRepositoryPostgres());
        cursoService.agregarObservador(new AuditoriaCursoObserver());

        // El cliente solo conoce la fachada.
        SistemaAcademicoFacade sistema =
                new SistemaAcademicoFacade(cursoService, estudianteService);

        int sufijo = 900000 + (int) (System.currentTimeMillis() % 99999);

        System.out.println("=== Registro mediante la fachada ===");
        sistema.registrarCurso(sufijo, "Ingeniería de Software", 4);
        sistema.registrarEstudiante(sufijo, "Ana Demo", "ana" + sufijo + "@demo.com");

        System.out.println("\n=== Resumen del sistema ===");
        System.out.print(sistema.resumen());

        System.out.println("\n=== Datos inválidos ===");
        try {
            sistema.registrarEstudiante(sufijo + 1, "Luis", "correo-sin-arroba");
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazado: " + e.getMessage());
        }

        // Limpieza de los datos de prueba.
        cursoService.eliminar(sufijo);
        estudianteService.eliminar(sufijo);
        System.out.println("\n(Datos de prueba eliminados)");
    }
}