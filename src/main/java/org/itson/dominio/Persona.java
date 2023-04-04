/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.itson.utils.ManejadorRFC;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "personas")
public class Persona implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    
    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno", nullable = true, length = 50)
    private String apellidoMaterno;
    
    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaNacimiento;
    
    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;
    
    @Column(name = "es_discapacitado", nullable = false)
    private Boolean esDiscapacitado;
    
    @OneToMany(mappedBy = "persona")
    private List<Tramite> tramites;

    public Persona() {
    }

    public Persona(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, Calendar fechaNacimiento, Boolean esDiscapacitado) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.esDiscapacitado = esDiscapacitado;
        this.setRfc();
    }

    public Persona(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, Calendar fechaNacimiento, Boolean esDiscapacitado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.esDiscapacitado = esDiscapacitado;
        this.setRfc();
    }

    public Persona(String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, Calendar fechaNacimiento, Boolean esDiscapacitado, List<Tramite> tramites) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.esDiscapacitado = esDiscapacitado;
        this.tramites = tramites;
        this.setRfc();
    }

    public Persona(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, Calendar fechaNacimiento, Boolean esDiscapacitado, List<Tramite> tramites) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.esDiscapacitado = esDiscapacitado;
        this.tramites = tramites;
        this.setRfc();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRfc() {
        return rfc;
    }

    public final void setRfc() {
        this.rfc = ManejadorRFC.generarRFC(this);
    }

    public Boolean getEsDiscapacitado() {
        return esDiscapacitado;
    }

    public void setEsDiscapacitado(Boolean esDiscapacitado) {
        this.esDiscapacitado = esDiscapacitado;
    }

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    public void addTramite(Tramite tramite) {
        if (this.tramites == null) {
            this.tramites = new ArrayList<>();
        }
        
        this.tramites.add(tramite);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + ", rfc=" + rfc + ", esDiscapacitado=" + esDiscapacitado + '}';
    }
}
