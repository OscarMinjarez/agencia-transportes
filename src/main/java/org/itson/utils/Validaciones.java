package org.itson.utils;

public class Validaciones {
    public static boolean campoVacio(String campo) {
        return campo.isEmpty() || campo.isBlank();
    }
    
    public static boolean comprobarFormatoNombre(String nombre) {
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean comprobarFormatoApellido(String apellido) {
        for (int i = 0; i < apellido.length(); i++) {
            char c = apellido.charAt(i);
            
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean comprobarFormatoAnho(String anho) {
        return anho.matches("\\d{4}");
    }
}
