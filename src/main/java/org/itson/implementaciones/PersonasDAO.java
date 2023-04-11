/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.implementaciones;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.dominio.Persona;
import org.itson.dto.PersonasDTO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPersonasDAO;

/**
 *
 * @author Oscar
 */
public class PersonasDAO implements IPersonasDAO {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;
    
    public PersonasDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }
    
    @Override
    public Persona buscar(Long id) {
        return this.ENTITY_MANAGER.find(Persona.class, id);
    }
    
    @Override
    public List<Persona> buscar(PersonasDTO parametrosPersona) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
        Root<Persona> persona = criteria.from(Persona.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosPersona.getNombres() != null) {
            filtros.add(builder.like(persona.get("nombres"), parametrosPersona.getNombres()));
        }
        
        if (parametrosPersona.getApellidoPaterno() != null) {
            filtros.add(builder.like(persona.get("apellidoPaterno"), parametrosPersona.getApellidoPaterno()));
        }
        
        if (parametrosPersona.getApellidoMaterno() != null) {
            filtros.add(builder.like(persona.get("apellidoMaterno"), parametrosPersona.getApellidoMaterno()));
        }
        
        if (parametrosPersona.getAnhoNacimiento() != null) {
            Expression<Integer> anho = builder.function("YEAR", Integer.class, persona.get("fechaNacimiento"));
            filtros.add(builder.equal(anho, parametrosPersona.getAnhoNacimiento()));
        }
        
        if (parametrosPersona.getRfc() != null) {
            filtros.add(builder.equal(persona.get("rfc"), parametrosPersona.getRfc()));
        }
        
        criteria.select(persona).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Persona> query = ENTITY_MANAGER.createQuery(criteria);
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return query.getResultList();
    }
    
    @Override
    public Persona insertar(Persona persona) {
        if (persona == null) {
            return null;
        }
        
        // TODO: Agregar validaciones y formateo de persona.
        this.ENTITY_MANAGER.getTransaction().begin();
        this.ENTITY_MANAGER.persist(persona);
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return persona;
    }
}
