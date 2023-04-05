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
public class PlacasDTO {
    private String textoPlaca;
    private Boolean esActiva;
    private Calendar fechaRecepcion;

    public PlacasDTO() {
    }

    public PlacasDTO(String textoPlaca, Boolean esActiva, Calendar fechaRecepcion) {
        this.textoPlaca = textoPlaca;
        this.esActiva = esActiva;
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getTextoPlaca() {
        return textoPlaca;
    }

    public void setTextoPlaca(String textoPlaca) {
        this.textoPlaca = textoPlaca;
    }

    public Boolean getEsActiva() {
        return esActiva;
    }

    public void setEsActiva(Boolean esActiva) {
        this.esActiva = esActiva;
    }

    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    @Override
    public String toString() {
        return "PlacasDTO{" + "textoPlaca=" + textoPlaca + ", esActiva=" + esActiva + ", fechaRecepcion=" + fechaRecepcion + '}';
    }
}
