/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "tramites")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_tramite")
public abstract class Tramite implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(name = "fecha_emision", nullable = false)
    @Temporal(TemporalType.DATE)
    protected Calendar fechaEmision;
    
    @Column(name = "monto", nullable = false)
    protected Float monto;
    
    @ManyToOne()
    @JoinColumn(name = "id_persona", nullable = true)
    protected Persona persona;

    public Tramite() {
    }

    public Tramite(Calendar fechaEmision, Float monto) {
        this.fechaEmision = fechaEmision;
        this.monto = monto;
    }

    public Tramite(Long id, Calendar fechaEmision, Float monto) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
    }

    public Tramite(Calendar fechaEmision, Float monto, Persona persona) {
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.persona = persona;
    }

    public Tramite(Long id, Calendar fechaEmision, Float monto, Persona persona) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        if (!(object instanceof Tramite)) {
            return false;
        }
        Tramite other = (Tramite) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Tramite{" + "id=" + id + ", fechaEmision=" + fechaEmision + ", monto=" + monto + ", persona=" + persona + '}';
    }
}
