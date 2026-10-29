package com.academico.infrastructure.observer;

import com.academico.application.observer.CursoObserver;
import com.academico.domain.model.Curso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Observador concreto 1: deja constancia de cada curso registrado.
 */
public class AuditoriaCursoObserver implements CursoObserver {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void cursoRegistrado(Curso curso) {
        System.out.println("[AUDITORÍA " + LocalDateTime.now().format(FORMATO)
                + "] Curso registrado: id=" + curso.getId()
                + ", nombre=" + curso.getNombre()
                + ", créditos=" + curso.getCreditos());
    }
}