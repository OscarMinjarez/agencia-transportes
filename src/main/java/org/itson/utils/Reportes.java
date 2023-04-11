package org.itson.utils;

import java.util.List;

import org.itson.dominio.Persona;
import org.itson.interfaces.IConexionBD;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.itson.dominio.Licencia;
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;

public class Reportes {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public Reportes(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }

    public List<Tramite> reporteTramites() {
        CriteriaBuilder builder = this.ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteriaQuery = builder.createQuery(Tramite.class);
        Root<Persona> personaRoot = criteriaQuery.from(Persona.class);
        Join<Persona, Tramite> tramiteJoin = personaRoot.join("tramites", JoinType.INNER);

        criteriaQuery.select(tramiteJoin);
        return this.ENTITY_MANAGER.createQuery(criteriaQuery).getResultList();
    }

    public List<Persona> reportePlacas() {
        CriteriaBuilder builder = this.ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
        Root<Persona> persona = criteria.from(Persona.class);
        Join<Persona, Placa> placas = persona.join("placas");
        
        criteria.select(persona);
        
        return this.ENTITY_MANAGER.createQuery(criteria).getResultList();
    }
    
    public List<Persona> reporteLicencias() {
        CriteriaBuilder builder = this.ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
        Root<Persona> persona = criteria.from(Persona.class);
        Join<Persona, Licencia> licencias = persona.join("licencias");
        
        criteria.select(persona);
        
        return this.ENTITY_MANAGER.createQuery(criteria).getResultList();
    }
}
