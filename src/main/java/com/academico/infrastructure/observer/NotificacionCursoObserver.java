package com.academico.infrastructure.observer;

import com.academico.application.observer.CursoObserver;
import com.academico.domain.model.Curso;

/**
 * Observador concreto 2: simula avisar a los estudiantes
 * que hay un nuevo curso disponible.
 */
public class NotificacionCursoObserver implements CursoObserver {

    @Override
    public void cursoRegistrado(Curso curso) {
        System.out.println("[NOTIFICACIÓN] Nuevo curso disponible para matrícula: "
                + curso.getNombre() + " (" + curso.getCreditos() + " créditos)");
    }
}