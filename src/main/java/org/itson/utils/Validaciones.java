package org.itson.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    
    public static String formatearFecha(GregorianCalendar fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha.getTime());
    }
    
    public static String calcularEdad(GregorianCalendar fechaNacimiento) {
        GregorianCalendar ahora = new GregorianCalendar();
        int anhoActual = ahora.get(Calendar.YEAR);
        int mesActual = ahora.get(Calendar.MONTH) + 1;
        int diaActual = ahora.get(Calendar.DAY_OF_MONTH);

        int anhoNacimiento = fechaNacimiento.get(Calendar.YEAR);
        int mesNacimiento = fechaNacimiento.get(Calendar.MONTH) + 1;
        int diaNacimiento = fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        int edad = anhoActual - anhoNacimiento;

        if (mesActual < mesNacimiento) {
            edad--;
        } else if (mesActual == mesNacimiento && diaActual < diaNacimiento) {
            edad--;
        }

        return Integer.toString(edad);
    }
}
