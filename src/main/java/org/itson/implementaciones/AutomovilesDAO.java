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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.dominio.Automovil;
import org.itson.dto.AutomovilesDTO;
import org.itson.interfaces.IAutomovilesDAO;
import org.itson.interfaces.IConexionBD;

/**
 *
 * @author Oscar
 */
public class AutomovilesDAO implements IAutomovilesDAO {
    
    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public AutomovilesDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }

    @Override
    public Automovil buscar(Long id) {
        return this.ENTITY_MANAGER.find(Automovil.class, id);
    }

    @Override
    public List<Automovil> buscar(AutomovilesDTO parametrosAutomovil) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Automovil> criteria = builder.createQuery(Automovil.class);
        Root<Automovil> automovil = criteria.from(Automovil.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosAutomovil.getSerie() != null) {
            filtros.add(builder.equal(automovil.get("serie"), parametrosAutomovil.getSerie()));
        }
        
        if (parametrosAutomovil.getMarca() != null) {
            filtros.add(builder.equal(automovil.get("marca"), parametrosAutomovil.getMarca()));
        }
        
        if (parametrosAutomovil.getLinea() != null) {
            filtros.add(builder.equal(automovil.get("linea"), parametrosAutomovil.getLinea()));
        }
        
        if (parametrosAutomovil.getModelo() != null) {
            filtros.add(builder.equal(automovil.get("modelo"), parametrosAutomovil.getModelo()));
        }
        
        if (parametrosAutomovil.getColor() != null) {
            filtros.add(builder.equal(automovil.get("color"), parametrosAutomovil.getColor()));
        }
        
        criteria.select(automovil).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Automovil> query = ENTITY_MANAGER.createQuery(criteria);
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return query.getResultList();
    }

    @Override
    public Automovil insertar(Automovil automovil) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
