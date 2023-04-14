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
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;
import org.itson.dto.LicenciasDTO;
import org.itson.dto.PlacasDTO;
import org.itson.dto.TramitesDTO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.ITramitesDAO;

/**
 *
 * @author Oscar
 */
public class TramitesDAO implements ITramitesDAO {
    
    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public TramitesDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }

    @Override
    public Tramite buscar(Long id) {
        return this.ENTITY_MANAGER.find(Tramite.class, id);
    }

    @Override
    public List<Tramite> buscar(TramitesDTO parametrosTramite) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
        Root<Tramite> tramite = criteria.from(Tramite.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosTramite.getFechaEmision() != null) {
            filtros.add(builder.equal(tramite.get("fechaEmision"), parametrosTramite.getFechaEmision()));
        }
        
        if (parametrosTramite.getMonto() != null) {
            filtros.add(builder.equal(tramite.get("monto"), parametrosTramite.getMonto()));
        }
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        criteria.select(tramite).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Tramite> query = ENTITY_MANAGER.createQuery(criteria);
        
        return query.getResultList();
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
    public Tramite insertar(Tramite tramite) {
        if (tramite == null) {
            return null;
        }
        
        System.out.println("Salu2");
        this.ENTITY_MANAGER.getTransaction().begin();
        this.ENTITY_MANAGER.persist(tramite);
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return tramite;
    }
}