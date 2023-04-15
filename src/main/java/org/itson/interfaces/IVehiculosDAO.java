/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Automovil;
import org.itson.dominio.Vehiculo;
import org.itson.dto.AutomovilesDTO;
import org.itson.dto.VehiculosDTO;

/**
 *
 * @author Oscar
 */
public interface IVehiculosDAO {
    Vehiculo buscar(Long id);
    List<Vehiculo> buscar(VehiculosDTO parametrosVehiculo);
    List<Automovil> buscar(AutomovilesDTO parametrosAutomovil);
    Vehiculo insertar(Vehiculo vehiculo);
    Vehiculo actualizar(Vehiculo vehiculo);
}
