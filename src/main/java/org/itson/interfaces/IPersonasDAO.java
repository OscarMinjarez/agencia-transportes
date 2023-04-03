/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Persona;
import org.itson.dto.PersonasDTO;

/**
 *
 * @author Oscar
 */
public interface IPersonasDAO {
    Persona buscar(Long id);
    List<Persona> buscar(PersonasDTO persona);
    Persona insertar(Persona persona);
}
