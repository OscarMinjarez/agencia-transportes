/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dto;

/**
 *
 * @author Oscar
 */
public class AutomovilesDTO {
    private String serie;
    private String marca;
    private String linea;
    private String modelo;
    private String color;

    public AutomovilesDTO() {
    }

    public AutomovilesDTO(String serie, String marca, String linea, String modelo, String color) {
        this.serie = serie;
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.color = color;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "VehiculosDTO{" + "serie=" + serie + ", marca=" + marca + ", linea=" + linea + ", modelo=" + modelo + ", color=" + color + '}';
    }
}
