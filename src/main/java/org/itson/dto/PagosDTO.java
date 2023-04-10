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
public class PagosDTO {
    private Calendar fechaPago;
    private Float monto;

    public PagosDTO() {
    }

    public PagosDTO(Calendar fechaPago, Float monto) {
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Calendar getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Calendar fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "PagosDTO{" + "fechaPago=" + fechaPago + ", monto=" + monto + '}';
    }
}
