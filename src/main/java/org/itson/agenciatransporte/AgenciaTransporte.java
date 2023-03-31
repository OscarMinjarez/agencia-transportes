/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.agenciatransporte;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.itson.dominio.Automovil;
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;
import org.itson.dominio.Vehiculo;
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
        
//        Persona persona = new Persona("Oscar", "Minjarez", "Zavala", "6444071684", new GregorianCalendar(2001, Calendar.MAY, 14), "MIZO010514CU4", false);
//        Pago pago = new Pago(new GregorianCalendar(), 500f);
//        Licencia licencia = new Licencia(new GregorianCalendar(2023, Calendar.MARCH, 29), new GregorianCalendar(2026, Calendar.MARCH, 29), 500f);
//        Tramite placa = new Placa("ABC-123", null, true, new GregorianCalendar(2023, Calendar.MARCH, 29), 1500f);
//        
//        Vehiculo automovil = new Automovil("12345", "Toyota", "Tacoma", "2022", "Negro");
        
        emManager.getTransaction().begin();
//        emManager.persist(persona);
//        emManager.persist(pago);
//        emManager.persist(licencia);
//        emManager.persist(placa);

//        emManager.persist(placa);
//        emManager.persist(automovil);
        
//        Tramite tramite1 = emManager.find(Tramite.class, 1l);
//        Persona persona1 = emManager.find(Persona.class, 1l);
//        tramite1.setPersona(persona1);
//        tramite1.setPago(emManager.find(Pago.class, 1l));
//        persona1.addTramite(emManager.find(Tramite.class, 1l));

        Vehiculo vehiculo1 = emManager.find(Automovil.class, 1l);
        Placa placa1 = emManager.find(Placa.class, 1l);
        vehiculo1.addPlaca(placa1);
        placa1.setVehiculo(vehiculo1);
        
        emManager.getTransaction().commit();
    }
}
