/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Licencia;
import org.itson.dto.LicenciasDTO;

/**
 *
 * @author Oscar
 */
public interface ILicenciasDAO {
    Licencia buscar(Long id);
    List<Licencia> buscar(LicenciasDTO parametrosLicencia);
    Licencia insertar(Licencia licencia);
}
