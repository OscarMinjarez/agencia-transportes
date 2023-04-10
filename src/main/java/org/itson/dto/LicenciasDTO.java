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
    private Calendar fechaEmision;
    private Float monto;
    private Calendar fechaExpedicion;

    public LicenciasDTO() {
    }

    public LicenciasDTO(Calendar fechaEmision, Float monto, Calendar fechaExpedicion) {
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.fechaExpedicion = fechaExpedicion;
    }

    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Calendar getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Calendar fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    @Override
    public String toString() {
        return "LicenciasDTO{" + "fechaEmision=" + fechaEmision + ", monto=" + monto + ", fechaExpedicion=" + fechaExpedicion + '}';
    }
}
