/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.itson.dominio.Persona;
import org.itson.implementaciones.PersonasDAO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPersonasDAO;

/**
 *
 * @author Oscar
 */
public class GenerarPersonasAleatorias {
    private static final int EDAD_MINIMA = 18;
    private static final int EDAD_MAXIMA = 60;

    private static final String[] NOMBRES = {"Juan", "Pedro", "Maria", "Ana", "Luis", "Pablo", "Carlos", "Jorge", "Miguel", "Raul"};
    private static final String[] APELLIDOS_PATERNO = {"Garcia", "Hernandez", "Lopez", "Martinez", "Gonzalez", "Rodriguez", "Perez", "Sanchez", "Ramirez", "Flores"};
    private static final String[] APELLIDOS_MATERNO = {"Fernandez", "Gonzalez", "Gomez", "Santos", "Mendez", "Vazquez", "Cruz", "Torres", "Reyes", "Rojas"};
    
    private static final Random random = new Random();
    
    private final IConexionBD MANEJADOR_CONEXIONES;
    private final IPersonasDAO persona;

    public GenerarPersonasAleatorias(IConexionBD manejadorConexiones) {
        this.MANEJADOR_CONEXIONES = manejadorConexiones;
        this.persona = new PersonasDAO(this.MANEJADOR_CONEXIONES);
    }
    
    public void generarPersonas() {
        for (int i = 0; i < 20; i++) {
            String nombres = NOMBRES[random.nextInt(NOMBRES.length)];
            String apellidoPaterno = APELLIDOS_PATERNO[random.nextInt(APELLIDOS_PATERNO.length)];
            String apellidoMaterno = APELLIDOS_MATERNO[random.nextInt(APELLIDOS_MATERNO.length)];
            String telefono = generarTelefono();
            Calendar fechaNacimiento = generarFechaNacimiento();
            boolean esDiscapacitado = random.nextBoolean();
            
            System.out.println(persona.insertar(new Persona(nombres, apellidoPaterno, apellidoMaterno, telefono, fechaNacimiento, esDiscapacitado)));
        }
    }
    
    private static String generarTelefono() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    private static Calendar generarFechaNacimiento() {
        int edad = random.nextInt(EDAD_MAXIMA - EDAD_MINIMA + 1) + EDAD_MINIMA;
        Calendar fechaActual = new GregorianCalendar();
        Calendar fechaNacimiento = new GregorianCalendar();
        fechaNacimiento.set(Calendar.YEAR, fechaActual.get(Calendar.YEAR) - edad);
        fechaNacimiento.set(Calendar.DAY_OF_YEAR, random.nextInt(fechaNacimiento.getActualMaximum(Calendar.DAY_OF_YEAR) + 1));
        return fechaNacimiento;
    }
}
