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
import org.itson.dominio.Vehiculo;
import org.itson.dto.VehiculosDTO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IVehiculosDAO;

/**
 *
 * @author Oscar
 */
public class VehiculosDAO implements IVehiculosDAO {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;

    public VehiculosDAO(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
    }
    
    @Override
    public Vehiculo buscar(Long id) {
        return this.ENTITY_MANAGER.find(Vehiculo.class, id);
    }

    @Override
    public List<Vehiculo> buscar(VehiculosDTO parametrosVehiculo) {
        this.ENTITY_MANAGER.getTransaction().begin();
        
        CriteriaBuilder builder = ENTITY_MANAGER.getCriteriaBuilder();
        CriteriaQuery<Vehiculo> criteria = builder.createQuery(Vehiculo.class);
        Root<Vehiculo> vehiculo = criteria.from(Vehiculo.class);
        List<Predicate> filtros = new ArrayList<>();
        
        if (parametrosVehiculo.getSerie() != null) {
            filtros.add(builder.equal(vehiculo.get("serie"), parametrosVehiculo.getSerie()));
        }
        
        if (parametrosVehiculo.getMarca() != null) {
            filtros.add(builder.equal(vehiculo.get("marca"), parametrosVehiculo.getMarca()));
        }
        
        if (parametrosVehiculo.getLinea() != null) {
            filtros.add(builder.equal(vehiculo.get("linea"), parametrosVehiculo.getLinea()));
        }
        
        if (parametrosVehiculo.getModelo() != null) {
            filtros.add(builder.equal(vehiculo.get("modelo"), parametrosVehiculo.getModelo()));
        }
        
        if (parametrosVehiculo.getColor() != null) {
            filtros.add(builder.equal(vehiculo.get("color"), parametrosVehiculo.getColor()));
        }
        
        criteria.select(vehiculo).where(builder.or(filtros.toArray(Predicate[]::new)));
        TypedQuery<Vehiculo> query = ENTITY_MANAGER.createQuery(criteria);
        
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return query.getResultList();
    }

    @Override
    public Vehiculo insertar(Vehiculo vehiculo) {
        if (vehiculo != null) {
            return null;
        }
        
         // TODO: Agregar validaciones y formateo de vehiculo.
        this.ENTITY_MANAGER.getTransaction().begin();
        this.ENTITY_MANAGER.persist(vehiculo);
        this.ENTITY_MANAGER.getTransaction().commit();
        
        return vehiculo;
    }
    
}
