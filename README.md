# Sistema de Gestión Académica - Arquitectura Hexagonal

Proyecto del curso de Arquitectura de Software (UNSCH - Ingeniería de Sistemas).
**Autor:** Dalshin Moysef Zamora Navarro

## Descripción
Aplicación de consola en Java que gestiona **estudiantes** y **cursos**
(registrar, listar, actualizar y eliminar) con arquitectura limpia
(hexagonal). Los datos se guardan en **PostgreSQL**; para los cursos
también existe un adaptador de respaldo en archivos JSON.

## Arquitectura

```
presentation  ->  application  ->  domain  <-  infrastructure
 (UI consola)     (casos de uso)   (modelo +     (adaptadores:
                                    interfaces)   PostgreSQL, JSON)
```

Las dependencias apuntan hacia adentro. El dominio no conoce ni
PostgreSQL ni JSON: define las interfaces y la infraestructura las
implementa.

```
com.academico
├── Main.java                      <- arma todas las piezas
├── application
│   ├── port/in                    <- puertos de entrada (casos de uso)
│   │   ├── EstudianteUseCase
│   │   └── CursoUseCase
│   └── service
│       ├── EstudianteService
│       └── CursoService
├── domain
│   ├── model                      <- entidades
│   │   ├── Estudiante
│   │   └── Curso
│   └── repository                 <- puertos de salida (interfaces)
│       ├── EstudianteRepository
│       └── CursoRepository
├── infrastructure/adapter/out/persistence
│   ├── EstudianteRepositoryPostgres
│   ├── EstudianteRepositoryJson
│   ├── CursoRepositoryPostgres
│   └── CursoRepositoryJson
└── presentation
    ├── EstudianteUI
    └── CursoUI
```

## Base de datos
El script `database/schema.sql` crea las tablas `estudiantes` y `cursos`.

1. Crear la base: `CREATE DATABASE sistema_gestion_academica;`
2. Ejecutar el script sobre esa base:
   `psql -U postgres -d sistema_gestion_academica -f database/schema.sql`

## Configuración
La conexión se lee de variables de entorno (la contraseña nunca va en el código):

| Variable | Por defecto |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/sistema_gestion_academica` |
| `DB_USER` | `postgres` |
| `DB_PASSWORD` | (obligatoria) |

## Requisitos
- JDK 24
- Maven (descarga Gson y el driver JDBC de PostgreSQL)
- PostgreSQL 18

## Ejecución
Ejecutar la clase `com.academico.Main`. Para cambiar de PostgreSQL a JSON
basta con cambiar el adaptador que se crea en `Main`, sin tocar los servicios.