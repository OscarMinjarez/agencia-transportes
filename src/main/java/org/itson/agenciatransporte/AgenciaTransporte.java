/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.agenciatransporte;

import javax.persistence.EntityManager;
import org.itson.dto.PersonasDTO;
import org.itson.implementaciones.ConexionBD;
import org.itson.implementaciones.PersonasDAO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPersonasDAO;
import org.itson.utils.ManejadorRFC;

/**
 *
 * @author Oscar
 */
public class AgenciaTransporte {

    public static void main(String[] args) {
        IConexionBD conexion = new ConexionBD("org.itson_AgenciaTransporte");
        EntityManager emManager = conexion.crearConexion();
        
//        Persona persona1 = new Persona("Oscar", "Minjarez", "Zavala", "6444071684", new GregorianCalendar(2001, Calendar.MAY, 14), false);
//        Persona persona2 = new Persona("Oscar", "Ramirez", null, "5509820481", new GregorianCalendar(1989, Calendar.APRIL, 22), "RAXO890422HU3", true);
//        Persona persona3 = new Persona("Josue Samuel", "Cruz", "Mata", "6441730523", new GregorianCalendar(1987, Calendar.DECEMBER, 18), false);
//        Pago pago = new Pago(new GregorianCalendar(), 500f);
//        Licencia licencia = new Licencia(new GregorianCalendar(2023, Calendar.MARCH, 29), new GregorianCalendar(2026, Calendar.MARCH, 29), 500f);
//        Tramite placa = new Placa("ABC-123", null, true, new GregorianCalendar(2023, Calendar.MARCH, 29), 1500f);
//        
//        Vehiculo automovil = new Automovil("12345", "Toyota", "Tacoma", "2022", "Negro");
        
        emManager.getTransaction().begin();
//        emManager.persist(persona1);
//        emManager.persist(persona2);
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

//        Vehiculo vehiculo1 = emManager.find(Automovil.class, 1l);
//        Placa placa1 = emManager.find(Placa.class, 1l);
//        vehiculo1.addPlaca(placa1);
//        placa1.setVehiculo(vehiculo1);

        emManager.getTransaction().commit();
//        
        IPersonasDAO personaDAO = new PersonasDAO(conexion);
//        
        PersonasDTO persona = new PersonasDTO();
        persona.setNombres("Josue");
        persona.setApellidoPaterno("Cruz");
//        persona.setAnhoNacimiento(2001);
        
        System.out.println(personaDAO.buscar(persona));
        System.out.println(ManejadorRFC.validarRFC(personaDAO.buscar(3l).getRfc()));
    }
}
