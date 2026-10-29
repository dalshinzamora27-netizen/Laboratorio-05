package com.academico.application.service;

import com.academico.application.observer.CursoObserver;
import com.academico.application.port.in.CursoUseCase;
import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.util.ArrayList;
import java.util.List;

public class CursoService implements CursoUseCase {

    private final CursoRepository repository;

    // PATRÓN OBSERVER: lista de observadores interesados en los cursos.
    private final List<CursoObserver> observadores = new ArrayList<>();

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    // Suscribe un observador.
    public void agregarObservador(CursoObserver observador) {
        observadores.add(observador);
    }

    // Da de baja a un observador.
    public void quitarObservador(CursoObserver observador) {
        observadores.remove(observador);
    }

    // Avisa a todos los observadores suscritos.
    private void notificarCursoRegistrado(Curso curso) {
        for (CursoObserver observador : observadores) {
            observador.cursoRegistrado(curso);
        }
    }

    @Override
    public void registrar(Curso curso) {
        repository.guardar(curso);
        // Solo se llega aquí si el guardado tuvo éxito.
        notificarCursoRegistrado(curso);
    }

    @Override
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    public boolean actualizar(Curso curso) {
        return repository.actualizar(curso);
    }

    @Override
    public boolean eliminar(int id) {
        return repository.eliminar(id);
    }
}