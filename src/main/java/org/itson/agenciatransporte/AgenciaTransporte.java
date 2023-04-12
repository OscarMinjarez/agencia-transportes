/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.agenciatransporte;

import org.itson.implementaciones.ConexionBD;
import org.itson.interfaces.IConexionBD;
import org.itson.presentacion.PantallaPrincipal;

/**
 *
 * @author Oscar
 */
public class AgenciaTransporte {

    public static void main(String[] args) {
        IConexionBD conexion = new ConexionBD("org.itson_AgenciaTransporte");
        
        new PantallaPrincipal(conexion).setVisible(true);
    }
}
