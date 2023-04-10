/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Automovil;
import org.itson.dto.AutomovilesDTO;

/**
 *
 * @author Oscar
 */
public interface IAutomovilesDAO {
    Automovil buscar(Long id);
    List<Automovil> buscar(AutomovilesDTO parametrosAutomovil);
    Automovil insertar(Automovil automovil);
}
