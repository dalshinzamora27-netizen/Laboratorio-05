package com.academico.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PATRÓN SINGLETON (creacional).
 * Garantiza una única instancia que concentra la configuración
 * de conexión a PostgreSQL y ofrece un único punto de acceso.
 */
public class ConfiguracionBD {

    // 1. Campo estático privado: guarda la única instancia.
    private static ConfiguracionBD instancia;

    private final String url;
    private final String usuario;
    private final String password;

    // 2. Constructor privado: nadie fuera de la clase puede hacer "new".
    private ConfiguracionBD() {
        this.url = System.getenv().getOrDefault(
                "DB_URL",
                "jdbc:postgresql://localhost:5432/sistema_gestion_academica"
        );
        this.usuario = System.getenv().getOrDefault("DB_USER", "postgres");
        this.password = System.getenv("DB_PASSWORD");

        System.out.println(
                "[ConfiguracionBD] Instancia creada (solo ocurre una vez)."
        );
    }

    // 3. Punto de acceso global. "synchronized" evita crear dos
    //    instancias si dos hilos llaman al mismo tiempo.
    public static synchronized ConfiguracionBD getInstance() {
        if (instancia == null) {
            instancia = new ConfiguracionBD();
        }
        return instancia;
    }

    public Connection crearConexion() throws SQLException {
        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Configura la variable de entorno DB_PASSWORD."
            );
        }
        return DriverManager.getConnection(url, usuario, password);
    }
}