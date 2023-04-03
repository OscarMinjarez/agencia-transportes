/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.implementaciones;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.itson.interfaces.IConexionBD;

/**
 *
 * @author Oscar
 */
public class ConexionBD implements IConexionBD {
    
    private final String CADENA_CONEXION;
    
    public ConexionBD(String CADENA_CONEXION) {
        this.CADENA_CONEXION = CADENA_CONEXION;
    }

    @Override
    public EntityManager crearConexion() {
        return Persistence.createEntityManagerFactory(this.CADENA_CONEXION).createEntityManager();
    }
}
