/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.agenciatransporte;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.itson.dominio.Licencia;
import org.itson.dominio.Placa;
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
        
        // Persona persona = new Persona("Oscar", "Minjarez", "Zavala", "6444071684", new GregorianCalendar(2001, Calendar.MAY, 14), "MIZO010514CU4", "MIZO010514HSRNVSA6", false);
        // Pago pago = new Pago(new GregorianCalendar(), 500f);
        Licencia licencia = new Licencia(new GregorianCalendar(2023, Calendar.MARCH, 29), new GregorianCalendar(2026, Calendar.MARCH, 29), 500f);
        
        Placa placa = new Placa("ABC-123", null, true, new GregorianCalendar(2023, Calendar.MARCH, 29), 1500f);
        
        emManager.getTransaction().begin();
        
        emManager.persist(licencia);
        emManager.persist(placa);
        
        emManager.getTransaction().commit();
    }
}
