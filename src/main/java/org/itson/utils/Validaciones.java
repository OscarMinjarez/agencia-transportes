package org.itson.utils;

public class Validaciones {
    public static boolean campoVacio(String campo) {
        return campo.isEmpty() || campo.isBlank();
    }
}
