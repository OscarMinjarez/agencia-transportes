/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Licencia;
import org.itson.dominio.Persona;
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;
import org.itson.dto.LicenciasDTO;
import org.itson.dto.PlacasDTO;
import org.itson.dto.TramitesDTO;

/**
 *
 * @author Oscar
 */
public interface ITramitesDAO {
    Tramite buscar(Long id);
    List<Tramite> buscar(TramitesDTO parametrosTramite);
    List<Placa> buscar(PlacasDTO parametrosPlaca);
    List<Licencia> buscar(LicenciasDTO parametrosLicencia);
    Tramite obtenerLicenciaPersona(Persona persona);
    Tramite insertar(Tramite tramite);
    Tramite actualizar(Tramite tramite);
}
