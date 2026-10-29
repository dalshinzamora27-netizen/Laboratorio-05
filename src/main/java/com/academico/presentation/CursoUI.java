package com.academico.presentation;

import com.academico.application.port.in.CursoUseCase;
import com.academico.domain.model.Curso;

import java.util.List;
import java.util.Scanner;

public class CursoUI {

    private final CursoUseCase service;

    public CursoUI(CursoUseCase service) {
        this.service = service;
    }

    public void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- CURSOS ---");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Regresar");
            opcion = leerEntero(sc, "Opción: ");

            switch (opcion) {
                case 1 -> registrar(sc);
                case 2 -> listar();
                case 3 -> actualizar(sc);
                case 4 -> eliminar(sc);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrar(Scanner sc) {
        int id = leerEntero(sc, "ID: ");
        String nombre = leerTexto(sc, "Nombre: ");
        int creditos = leerCreditos(sc, "Créditos: ");

        try {
            service.registrar(new Curso(id, nombre, creditos));
            System.out.println("Curso registrado.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listar() {
        try {
            List<Curso> cursos = service.listar();

            if (cursos.isEmpty()) {
                System.out.println("No hay cursos registrados.");
                return;
            }

            cursos.forEach(c ->
                    System.out.println(
                            c.getId() + " - " +
                                    c.getNombre() + " - " +
                                    c.getCreditos() + " créditos"
                    )
            );
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void actualizar(Scanner sc) {
        int id = leerEntero(sc, "ID del curso: ");
        String nombre = leerTexto(sc, "Nuevo nombre: ");
        int creditos = leerCreditos(sc, "Nuevos créditos: ");

        try {
            boolean actualizado = service.actualizar(new Curso(id, nombre, creditos));
            System.out.println(
                    actualizado ? "Curso actualizado." : "Curso no encontrado."
            );
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminar(Scanner sc) {
        int id = leerEntero(sc, "ID a eliminar: ");

        try {
            boolean eliminado = service.eliminar(id);
            System.out.println(
                    eliminado ? "Curso eliminado." : "Curso no encontrado."
            );
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---- Lectura segura de datos ----

    private int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private String leerTexto(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            if (!linea.isEmpty()) {
                return linea;
            }
            System.out.println("El campo no puede estar vacío.");
        }
    }

    private int leerCreditos(Scanner sc, String mensaje) {
        while (true) {
            int creditos = leerEntero(sc, mensaje);
            if (creditos > 0) {
                return creditos;
            }
            System.out.println("Los créditos deben ser mayores a 0.");
        }
    }
}