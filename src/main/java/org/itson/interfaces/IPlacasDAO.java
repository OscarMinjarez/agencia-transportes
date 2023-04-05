/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Placa;
import org.itson.dto.PlacasDTO;

/**
 *
 * @author Oscar
 */
public interface IPlacasDAO {
    Placa buscar(Long id);
    List<Placa> buscar(PlacasDTO placa);
    Placa insertar(Placa placa);
}
