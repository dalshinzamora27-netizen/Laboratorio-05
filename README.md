# Laboratorio 05 - Patrones de Diseño

**Curso:** IS488 Arquitectura de Software - UNSCH
**Docente:** Ing. Luis Adderlin Ruiz Huaman
**Estudiante:** Dalshin Moysef Zamora Navarro

Sistema de Gestión Académica (Estudiantes y Cursos) en Java con arquitectura
hexagonal y PostgreSQL, al que se le aplicaron cinco patrones de diseño.

## Patrones implementados

| Patrón | Tipo | Dónde está | Demo |
|---|---|---|---|
| Singleton | Creacional | `infrastructure/config/ConfiguracionBD` | `DemoSingleton` |
| Decorator | Estructural | `infrastructure/decorator/CursoRepositoryConLog` | `DemoDecorator` |
| Observer | Comportamiento | `application/observer` + `infrastructure/observer` | `DemoObserver` |
| Builder (extra) | Creacional | `domain/model/CursoBuilder` | `DemoBuilder` |
| Facade (extra) | Estructural | `application/facade/SistemaAcademicoFacade` | `DemoFacade` |

## Estructura

```
com.academico
├── application      (casos de uso, servicios, observer, facade)
├── domain           (modelos, repositorios, builder)
├── infrastructure   (adaptadores PostgreSQL/JSON, config, decorator, observers)
├── presentation     (menús de consola)
├── patrones         (demos de cada patrón)
└── Main.java        (raíz de composición)
```

## Requisitos

- JDK 24 y Maven
- PostgreSQL con la base `sistema_gestion_academica` (script en `database/schema.sql`)
- Variable de entorno `DB_PASSWORD` (obligatoria). Opcionales: `DB_URL`, `DB_USER`.

## Ejecución

Compilar con `mvn compile` y ejecutar `Main` (sistema completo) o cualquier clase
del paquete `patrones` para ver cada patrón por separado.