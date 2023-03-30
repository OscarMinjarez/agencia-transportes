/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.agenciatransporte;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.itson.dominio.Persona;
import org.itson.implementaciones.ConexionDB;
import org.itson.interfaces.IConexionDB;

/**
 *
 * @author Oscar
 */
public class AgenciaTransporte {

    public static void main(String[] args) {
        IConexionDB conexion = new ConexionDB("org.itson_AgenciaTransporte");
        EntityManager emManager = conexion.crearConexion();
        
        Persona persona = new Persona("Oscar", "Minjarez", "Zavala", "6444071684", new GregorianCalendar(2001, Calendar.MAY, 14), "MIZO010514CU4", "MIZO010514HSRNVSA6", false);
        
        emManager.getTransaction().begin();
        emManager.persist(persona);
        System.out.println("Se agregó la persona: " + persona);
        emManager.getTransaction().commit();
    }
}
