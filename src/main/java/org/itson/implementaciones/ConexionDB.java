/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.implementaciones;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.itson.interfaces.IConexionDB;

/**
 *
 * @author Oscar
 */
public class ConexionDB implements IConexionDB {
    
    private final String CADENA_CONEXION;
    
    public ConexionDB(String cadena_conexion) {
        this.CADENA_CONEXION = cadena_conexion;
    }

    @Override
    public EntityManager crearConexion() {
        return Persistence.createEntityManagerFactory(this.CADENA_CONEXION).createEntityManager();
    }
}
