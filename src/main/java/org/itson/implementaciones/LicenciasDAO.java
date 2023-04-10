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
import org.itson.dominio.Licencia;
import org.itson.dto.LicenciasDTO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.ILicenciasDAO;

/**
 *
 * @author Oscar
 */
public class LicenciasDAO implements ILicenciasDAO {
    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public LicenciasDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }
    
    @Override
    public Licencia buscar(Long id) {
        return this.ENTITY_MANAGER.find(Licencia.class, id);
    }

    @Override
    public List<Licencia> buscar(LicenciasDTO parametrosLicencia) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Licencia> criteria = builder.createQuery(Licencia.class);
        Root<Licencia> licencia = criteria.from(Licencia.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosLicencia.getFechaEmision() != null) {
            filtros.add(builder.equal(licencia.get("fechaEmision"), parametrosLicencia.getFechaEmision()));
        }
        
        if (parametrosLicencia.getMonto() != null) {
            filtros.add(builder.equal(licencia.get("monto"), parametrosLicencia.getMonto()));
        }
        
        if (parametrosLicencia.getFechaExpedicion() != null) {
            filtros.add(builder.equal(licencia.get("fechaExpedicion"), parametrosLicencia.getFechaExpedicion()));
        }
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        criteria.select(licencia).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Licencia> query = ENTITY_MANAGER.createQuery(criteria);
        
        return query.getResultList();
    }

    @Override
    public Licencia insertar(Licencia licencia) {
        if (licencia == null) {
            return null;
        }
        
        // TODO: Añadir validaciones de licencias.
        this.ENTITY_MANAGER.getTransaction().begin();
        this.ENTITY_MANAGER.persist(licencia);
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return licencia;
    }
}
