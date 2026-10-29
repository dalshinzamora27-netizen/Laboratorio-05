package com.academico.infrastructure.decorator;

import com.academico.domain.model.Curso;
import com.academico.domain.repository.CursoRepository;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * PATRÓN DECORATOR (estructural) - Decorador concreto.
 * Agrega un registro (log) de cada operación sobre los cursos,
 * sin modificar el repositorio original.
 */
public class CursoRepositoryConLog extends CursoRepositoryDecorator {

    private static final DateTimeFormatter HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public CursoRepositoryConLog(CursoRepository envuelto) {
        super(envuelto);
    }

    @Override
    public void guardar(Curso curso) {
        log("guardar -> id=" + curso.getId() + ", nombre=" + curso.getNombre());
        try {
            super.guardar(curso);   // delega en el objeto envuelto
            log("guardar OK");
        } catch (RuntimeException e) {
            log("guardar FALLÓ: " + e.getMessage());
            throw e;                // el error se sigue propagando igual
        }
    }

    @Override
    public List<Curso> listar() {
        List<Curso> cursos = super.listar();
        log("listar -> " + cursos.size() + " curso(s)");
        return cursos;
    }

    @Override
    public Optional<Curso> buscarPorId(int id) {
        Optional<Curso> curso = super.buscarPorId(id);
        log("buscarPorId(" + id + ") -> "
                + (curso.isPresent() ? "encontrado" : "no encontrado"));
        return curso;
    }

    @Override
    public boolean actualizar(Curso curso) {
        boolean actualizado = super.actualizar(curso);
        log("actualizar(id=" + curso.getId() + ") -> "
                + (actualizado ? "OK" : "no encontrado"));
        return actualizado;
    }

    @Override
    public boolean eliminar(int id) {
        boolean eliminado = super.eliminar(id);
        log("eliminar(id=" + id + ") -> "
                + (eliminado ? "OK" : "no encontrado"));
        return eliminado;
    }

    private void log(String mensaje) {
        System.out.println(
                "[LOG " + LocalTime.now().format(HORA) + "] " + mensaje
        );
    }
}