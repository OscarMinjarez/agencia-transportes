/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.interfaces;

import java.util.List;
import org.itson.dominio.Pago;
import org.itson.dto.PagosDTO;

/**
 *
 * @author Oscar
 */
public interface IPagosDAO {
    Pago buscar(Long id);
    List<Pago> buscar(PagosDTO parametrosPago);
    Pago insertar(Pago pago);
}
