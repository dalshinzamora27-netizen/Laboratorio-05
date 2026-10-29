package com.academico.application.facade;

import com.academico.application.port.in.CursoUseCase;
import com.academico.application.port.in.EstudianteUseCase;
import com.academico.domain.model.Curso;
import com.academico.domain.model.CursoBuilder;
import com.academico.domain.model.Estudiante;

import java.util.List;

/**
 * PATRÓN FACADE: interfaz única y simple sobre varios subsistemas
 * (servicios de cursos y estudiantes, builder de cursos).
 */
public class SistemaAcademicoFacade {

    private final CursoUseCase cursos;
    private final EstudianteUseCase estudiantes;

    public SistemaAcademicoFacade(CursoUseCase cursos, EstudianteUseCase estudiantes) {
        this.cursos = cursos;
        this.estudiantes = estudiantes;
    }

    // Valida con el Builder y registra el curso en un solo paso.
    public Curso registrarCurso(int id, String nombre, int creditos) {
        Curso curso = new CursoBuilder()
                .conId(id)
                .conNombre(nombre)
                .conCreditos(creditos)
                .build();
        cursos.registrar(curso);
        return curso;
    }

    // Valida lo básico y registra al estudiante.
    public Estudiante registrarEstudiante(int id, String nombre, String correo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo no es válido.");
        }
        Estudiante estudiante = new Estudiante(id, nombre.trim(), correo.trim());
        estudiantes.registrar(estudiante);
        return estudiante;
    }

    // Reúne datos de ambos subsistemas en un solo texto.
    public String resumen() {
        List<Curso> listaCursos = cursos.listar();
        List<Estudiante> listaEstudiantes = estudiantes.listar();

        StringBuilder sb = new StringBuilder();
        sb.append("Estudiantes registrados: ").append(listaEstudiantes.size()).append("\n");
        sb.append("Cursos registrados: ").append(listaCursos.size()).append("\n");
        for (Curso c : listaCursos) {
            sb.append("  - ").append(c.getNombre())
              .append(" (").append(c.getCreditos()).append(" créditos)\n");
        }
        return sb.toString();
    }
}