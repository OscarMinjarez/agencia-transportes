/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dto;

import java.util.Calendar;

/**
 *
 * @author Oscar
 */
public class LicenciasDTO {
    private Calendar fechaExpedicion;

    public LicenciasDTO() {
    }

    public LicenciasDTO(Calendar fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Calendar getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Calendar fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    @Override
    public String toString() {
        return "LicenciasDTO{" + "fechaExpedicion=" + fechaExpedicion + '}';
    }
}
