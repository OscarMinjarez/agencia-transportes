/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_pago", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaPago;
    
    @Column(name = "monto")
    private Float monto;
    
    @OneToOne()
    @JoinColumn(name = "id_tramite", nullable = true)
    protected Tramite tramite;

    public Pago() {
    }

    public Pago(Calendar fechaPago, Float monto) {
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Pago(Calendar fechaPago, Float monto, Tramite tramite) {
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.tramite = tramite;
    }

    public Pago(Long id, Calendar fechaPago, Float monto) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Pago(Long id, Calendar fechaPago, Float monto, Tramite tramite) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.tramite = tramite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
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
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Pago{" + "id=" + id + ", fechaPago=" + fechaPago + ", monto=" + monto + ", tramite=" + tramite + '}';
    }
}
