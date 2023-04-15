/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utils;

/**
 *
 * @author Oscar
 */
public class ManejadorNumeroDeSerie {

    public static String generarNumeroSerie() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int index = (int) (letras.length() * Math.random());
            builder.append(letras.charAt(index));
        }

        for (int i = 0; i < 3; i++) {
            int index = (int) (numeros.length() * Math.random());
            builder.append(numeros.charAt(index));
        }
        
        for (int i = 0; i < 3; i++) {
            int index = (int) (letras.length() * Math.random());
            builder.append(letras.charAt(index));
        }

        return builder.toString();
    }
}
