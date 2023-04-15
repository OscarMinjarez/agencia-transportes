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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oscar
 */
@Entity
@DiscriminatorValue("licencia")
public class Licencia extends Tramite implements Serializable {

    @Column(name = "fecha_expiracion", nullable = true)
    @Temporal(TemporalType.DATE)
    private Calendar fechaExpiracion;

    public Licencia() {
        super();
    }

    public Licencia(Calendar fechaExpedicion, Calendar fechaEmision, Float monto) {
        super(fechaEmision, monto);
        this.fechaExpiracion = fechaExpedicion;
    }

    public Licencia(Calendar fechaExpedicion, Long id, Calendar fechaEmision, Float monto) {
        super(id, fechaEmision, monto);
        this.fechaExpiracion = fechaExpedicion;
    }

    public Licencia(Calendar fechaExpedicion, Calendar fechaEmision, Float monto, Persona persona) {
        super(fechaEmision, monto, persona);
        this.fechaExpiracion = fechaExpedicion;
    }

    public Licencia(Calendar fechaExpedicion, Long id, Calendar fechaEmision, Float monto, Persona persona) {
        super(id, fechaEmision, monto, persona);
        this.fechaExpiracion = fechaExpedicion;
    }
    
    public Calendar getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Calendar fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
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
        return "Licencia{" + "fechaExpedicion=" + fechaExpiracion + '}';
    }
}
