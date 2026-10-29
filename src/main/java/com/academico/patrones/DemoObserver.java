package com.academico.patrones;

import com.academico.application.service.CursoService;
import com.academico.domain.model.Curso;
import com.academico.infrastructure.adapter.out.persistence.CursoRepositoryPostgres;
import com.academico.infrastructure.observer.AuditoriaCursoObserver;
import com.academico.infrastructure.observer.NotificacionCursoObserver;

public class DemoObserver {

    public static void main(String[] args) {
        CursoService servicio = new CursoService(new CursoRepositoryPostgres());

        AuditoriaCursoObserver auditoria = new AuditoriaCursoObserver();
        NotificacionCursoObserver notificacion = new NotificacionCursoObserver();

        // IDs aleatorios para poder repetir la demo sin duplicados.
        int id1 = 900000 + (int) (System.currentTimeMillis() % 99999);
        int id2 = id1 + 1;

        System.out.println("=== Con 2 observadores suscritos ===");
        servicio.agregarObservador(auditoria);
        servicio.agregarObservador(notificacion);
        servicio.registrar(new Curso(id1, "Patrones de Diseño", 4));

        System.out.println("\n=== Se da de baja la notificación ===");
        servicio.quitarObservador(notificacion);
        servicio.registrar(new Curso(id2, "Arquitectura Hexagonal", 3));

        // Limpieza: se borran los cursos de prueba.
        servicio.eliminar(id1);
        servicio.eliminar(id2);
        System.out.println("\n(Cursos de prueba eliminados)");
    }
}