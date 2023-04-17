package org.itson.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.itson.interfaces.IConexionBD;
import javax.persistence.EntityManager;
import org.itson.dominio.Persona;
import org.itson.dominio.Tramite;
import org.itson.dto.PersonasDTO;
import org.itson.implementaciones.PersonasDAO;
import org.itson.interfaces.IPersonasDAO;

public class Reportes {

    private final IConexionBD MANEJADOR_CONEXIONES;

    public Reportes(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
    }

    public List<Tramite> consultarReporte(PersonasDTO parametrosPersona, Calendar desde, Calendar hasta) {
        IPersonasDAO personasDAO = new PersonasDAO(this.MANEJADOR_CONEXIONES);
        
        if (parametrosPersona == null) {
            parametrosPersona = new PersonasDTO();
        }
        
        List<Persona> personas = personasDAO.buscar(parametrosPersona);
        
        if (personas.size() <= 0 || personas.isEmpty()) {
            return null;
        }
        
        if (desde != null && hasta != null) {
            return this.filtrarTramitesPorPeriodo(personas, desde, hasta);
        }
        
        return this.filtrarTramitesPorPersonas(personas);
    }
    
    private List<Tramite> filtrarTramitesPorPersonas(List<Persona> listaPersonas) {
        List<Tramite> tramitesFiltrados = new ArrayList<>();
        
        for (Persona persona : listaPersonas) {
            for (Tramite tramite : persona.getTramites()) {
                tramitesFiltrados.add(tramite);
            }
        }
        
        return tramitesFiltrados;
    }
    
    private List<Tramite> filtrarTramitesPorPeriodo(List<Persona> listaPersonas, Calendar desde, Calendar hasta) {
        List<Tramite> tramitesFiltrados = new ArrayList<>();
        
        for (Persona persona : listaPersonas) {
            for (Tramite tramite : persona.getTramites()) {
                if (ManejadorFechas.estaEnPeriodo(tramite.getFechaEmision(), desde, hasta)) {
                    tramitesFiltrados.add(tramite);
                }
            }
        }
        
        return tramitesFiltrados;
    }
}
