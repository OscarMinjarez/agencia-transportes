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
import org.itson.dominio.Placa;
import org.itson.dto.PlacasDTO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPlacasDAO;

/**
 *
 * @author Oscar
 */
public class PlacasDAO implements IPlacasDAO {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public PlacasDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }
    
    @Override
    public Placa buscar(Long id) {
        return this.ENTITY_MANAGER.find(Placa.class, id);
    }

    @Override
    public List<Placa> buscar(PlacasDTO parametrosPlaca) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Placa> criteria = builder.createQuery(Placa.class);
        Root<Placa> placa = criteria.from(Placa.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosPlaca.getFechaEmision() != null) {
            filtros.add(builder.equal(placa.get("fechaEmision"), parametrosPlaca.getFechaEmision()));
        }
        
        if (parametrosPlaca.getMonto() != null) {
            filtros.add(builder.equal(placa.get("monto"), parametrosPlaca.getMonto()));
        }
        
        if (parametrosPlaca.getTextoPlaca() != null) {
            filtros.add(builder.like(placa.get("textoPlaca"), parametrosPlaca.getTextoPlaca()));
        }
        
        if (parametrosPlaca.getEsActiva() != null) {
            filtros.add(builder.equal(placa.get("esActiva"), parametrosPlaca.getEsActiva()));
        }
        
        if (parametrosPlaca.getFechaRecepcion() != null) {
            filtros.add(builder.equal(placa.get("fechaRecepcion"), parametrosPlaca.getFechaRecepcion()));
        }
        
        criteria.select(placa).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Placa> query = ENTITY_MANAGER.createQuery(criteria);
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return query.getResultList();
    }

    @Override
    public Placa insertar(Placa placa) {
        if (placa == null) {
            return null;
        }
        
        // TODO: Agregar validaciones de plcas.
        this.ENTITY_MANAGER.getTransaction().begin();
        this.ENTITY_MANAGER.persist(placa);
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return placa;
    }
}
