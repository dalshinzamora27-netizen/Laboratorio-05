package com.academico.application.observer;

import com.academico.domain.model.Curso;

/**
 * PATRÓN OBSERVER: contrato que cumple todo objeto interesado
 * en enterarse de que se registró un curso.
 */
public interface CursoObserver {

    void cursoRegistrado(Curso curso);
}