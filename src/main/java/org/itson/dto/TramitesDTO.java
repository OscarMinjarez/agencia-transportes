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
public class TramitesDTO {
    private Calendar fechaEmision;
    private Float monto;

    public TramitesDTO() {
    }

    public TramitesDTO(Calendar fechaEmision, Float monto) {
        this.fechaEmision = fechaEmision;
        this.monto = monto;
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

    @Override
    public String toString() {
        return "TramitesDTO{" + "fechaEmision=" + fechaEmision + ", monto=" + monto + '}';
    }
}
