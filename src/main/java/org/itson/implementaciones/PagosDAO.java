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
import org.itson.dominio.Pago;
import org.itson.dto.PagosDTO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPagosDAO;

/**
 *
 * @author Oscar
 */
public class PagosDAO implements IPagosDAO {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public PagosDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }
    
    @Override
    public Pago buscar(Long id) {
        return this.ENTITY_MANAGER.find(Pago.class, id);
    }

    @Override
    public List<Pago> buscar(PagosDTO parametrosPago) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Pago> criteria = builder.createQuery(Pago.class);
        Root<Pago> pago = criteria.from(Pago.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosPago.getFechaPago() != null) {
            filtros.add(builder.equal(pago.get("fechaPago"), parametrosPago.getFechaPago()));
        }
        
        if (parametrosPago.getMonto() != null) {
            filtros.add(builder.equal(pago.get("monto"), parametrosPago.getMonto()));
        }
        
        criteria.select(pago).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Pago> query = ENTITY_MANAGER.createQuery(criteria);
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return query.getResultList();
    }

    @Override
    public Pago insertar(Pago pago) {
        if (pago == null) {
            return null;
        }
        
        this.ENTITY_MANAGER.getTransaction().begin();
        this.ENTITY_MANAGER.persist(pago);
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return pago;
    }
}
