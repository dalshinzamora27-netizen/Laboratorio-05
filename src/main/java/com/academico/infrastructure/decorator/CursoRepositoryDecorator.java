package com.academico.infrastructure.decorator;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

/**
 * PATRÓN DECORATOR (estructural) - Decorador base.
 * Implementa la misma interfaz que el objeto que envuelve y delega
 * todas las llamadas en él. Los decoradores concretos heredan de aquí
 * y sobrescriben solo lo que quieren ampliar.
 */
public abstract class CursoRepositoryDecorator implements CursoRepository {

    // El objeto envuelto (puede ser Postgres, JSON u otro decorador).
    protected final CursoRepository envuelto;

    protected CursoRepositoryDecorator(CursoRepository envuelto) {
        this.envuelto = envuelto;
    }

    @Override
    public void guardar(Curso curso) {
        envuelto.guardar(curso);
    }

    @Override
    public List<Curso> listar() {
        return envuelto.listar();
    }

    @Override
    public Optional<Curso> buscarPorId(int id) {
        return envuelto.buscarPorId(id);
    }

    @Override
    public boolean actualizar(Curso curso) {
        return envuelto.actualizar(curso);
    }

    @Override
    public boolean eliminar(int id) {
        return envuelto.eliminar(id);
    }
}