package com.academico.patrones;

import com.academico.infrastructure.config.ConfiguracionBD;

public class DemoSingleton {

    public static void main(String[] args) {
        ConfiguracionBD a = ConfiguracionBD.getInstance();
        ConfiguracionBD b = ConfiguracionBD.getInstance();

        System.out.println("Identidad de a: " + System.identityHashCode(a));
        System.out.println("Identidad de b: " + System.identityHashCode(b));
        System.out.println("¿Es la misma instancia? " + (a == b));
    }
}
