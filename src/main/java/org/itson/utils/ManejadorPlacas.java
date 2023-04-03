/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Oscar
 */
public class ManejadorPlacas {
    public static String generarPlaca() {
        String cadena = "";
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] alfabetoArray = alfabeto.toCharArray();
        
        for (int i = 0; i < 3; i++) {
            cadena += alfabetoArray[generarEntre(0, alfabeto.length() - 1)];
        }
        
        cadena += "-";
        
        for (int i = 0; i < 3; i++) {
            cadena += generarEntre(0, 9);
        }
        
        return cadena;
    }
    
    public static boolean validarPlaca(String placa) {
        String regex = "^[A-Z]{3}-\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa.toUpperCase());
        return matcher.matches();
    }
    
    private static int generarEntre(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
