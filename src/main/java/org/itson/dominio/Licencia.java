/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "licencias")
@DiscriminatorValue(value = "licencia")
public class Licencia extends Tramite implements Serializable {

    @Column(name = "fecha_expedicion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaExpedicion;

    public Licencia() {
        super();
    }

    public Licencia(Calendar fechaExpedicion, Calendar fechaEmision, Float monto) {
        super(fechaEmision, monto);
        this.fechaExpedicion = fechaExpedicion;
    }

    public Licencia(Calendar fechaExpedicion, Long id, Calendar fechaEmision, Float monto) {
        super(id, fechaEmision, monto);
        this.fechaExpedicion = fechaExpedicion;
    }
    
    public Calendar getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Calendar fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
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
        if (!(object instanceof Licencia)) {
            return false;
        }
        Licencia other = (Licencia) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaExpedicion=" + fechaExpedicion + '}';
    }
}
