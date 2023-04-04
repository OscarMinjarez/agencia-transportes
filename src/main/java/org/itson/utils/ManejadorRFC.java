/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utils;

import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.itson.dominio.Persona;

/**
 *
 * @author Oscar
 */
public class ManejadorRFC {
    public static String generarRFC(Persona persona) {
        String apellidoMaterno = (persona.getApellidoMaterno() != null) ? persona.getApellidoMaterno() : "X";
        String apellidoPaterno = persona.getApellidoPaterno();
        String nombres = persona.getNombres();
        Calendar fechaNacimiento = persona.getFechaNacimiento();

        String primerasDosLetrasApellidoPaterno = apellidoPaterno.substring(0, Math.min(apellidoPaterno.length(), 2)).toUpperCase();

        int anio = fechaNacimiento.get(Calendar.YEAR) % 100;
        int mes = fechaNacimiento.get(Calendar.MONTH) + 1;
        int dia = fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        Random rand = new Random();
        String dosLetras = String.valueOf((char)(rand.nextInt(26) + 'A')) + String.valueOf((char)(rand.nextInt(26) + 'A'));
        int numero = rand.nextInt(10);

        return primerasDosLetrasApellidoPaterno + 
                     apellidoMaterno.charAt(0) + 
                     nombres.charAt(0) + 
                     String.format("%02d", anio) +
                     String.format("%02d", mes) + 
                     String.format("%02d", dia) +
                     dosLetras + 
                     numero;
    }
    
    public static boolean validarRFC(String rfc) {
        String regex = "^([A-ZÑ&]{4})" + // Las tres o cuatro letras iniciales
                       "([\\d]{2})" + // Los dos dígitos del año de nacimiento
                       "((0[1-9])|(1[0-2]))" + // El mes de nacimiento con cero inicial
                       "(([0-2][1-9])|(3[0-1]))" + // El día de nacimiento con cero inicial
                       "([A-Z\\d]{3})?$"; // Los tres caracteres finales, que pueden ser letras o dígitos

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rfc);

        return matcher.matches();
    }
}
