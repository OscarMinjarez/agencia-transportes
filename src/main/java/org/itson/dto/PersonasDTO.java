/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dto;

/**
 *
 * @author Oscar
 */
public class PersonasDTO {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer anhoNacimiento;
    private String rfc;

    public PersonasDTO() {
    }

    public PersonasDTO(String nombres, String apellidoPaterno, String apellidoMaterno, Integer anhoNacimiento, String rfc) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.anhoNacimiento = anhoNacimiento;
        this.rfc = rfc;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getAnhoNacimiento() {
        return anhoNacimiento;
    }

    public void setAnhoNacimiento(Integer anhoNacimiento) {
        this.anhoNacimiento = anhoNacimiento;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public String toString() {
        return "PersonasDTO{" + "nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + anhoNacimiento + ", rfc=" + rfc + '}';
    }
}
