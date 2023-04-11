/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.itson.utils.ManejadorPlacas;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "placas")
public class Placa extends Tramite implements Serializable {

    @Column(name = "texto_placa", nullable = false, length = 7)
    private String textoPlaca;
    
    @Column(name = "fecha_recepcion", nullable = true)
    @Temporal(TemporalType.DATE)
    private Calendar fechaRecepcion;
    
    @Column(name = "es_activa", nullable = false)
    private Boolean esActiva;
    
    @ManyToOne()
    @JoinColumn(name = "id_vehiculo", nullable = true)
    private Vehiculo vehiculo;
    
    @OneToOne()
    @JoinColumn(name = "id_persona", nullable = true)
    private Persona placaPersona;

    public Placa() {
        super();
    }

    public Placa(Calendar fechaRecepcion, Boolean esActiva, Calendar fechaEmision, Float monto) {
        super(fechaEmision, monto);
        this.fechaRecepcion = fechaRecepcion;
        this.esActiva = esActiva;
        this.setTextoPlaca();
    }

    public Placa(Calendar fechaRecepcion, Boolean esActiva, Long id, Calendar fechaEmision, Float monto) {
        super(id, fechaEmision, monto);
        this.fechaRecepcion = fechaRecepcion;
        this.esActiva = esActiva;
        this.setTextoPlaca();
    }

    public Placa(Calendar fechaRecepcion, Boolean esActiva, Vehiculo vehiculo, Calendar fechaEmision, Float monto) {
        super(fechaEmision, monto);
        this.fechaRecepcion = fechaRecepcion;
        this.esActiva = esActiva;
        this.vehiculo = vehiculo;
        this.setTextoPlaca();
    }

    public Placa(Calendar fechaRecepcion, Boolean esActiva, Vehiculo vehiculo, Long id, Calendar fechaEmision, Float monto) {
        super(id, fechaEmision, monto);
        this.fechaRecepcion = fechaRecepcion;
        this.esActiva = esActiva;
        this.vehiculo = vehiculo;
        this.setTextoPlaca();
    }

    public Placa(Calendar fechaRecepcion, Boolean esActiva, Vehiculo vehiculo, Persona persona, Calendar fechaEmision, Float monto) {
        super(fechaEmision, monto);
        this.fechaRecepcion = fechaRecepcion;
        this.esActiva = esActiva;
        this.vehiculo = vehiculo;
        this.placaPersona = persona;
        this.setTextoPlaca();
    }

    public Placa(Calendar fechaRecepcion, Boolean esActiva, Vehiculo vehiculo, Persona persona, Long id, Calendar fechaEmision, Float monto) {
        super(id, fechaEmision, monto);
        this.fechaRecepcion = fechaRecepcion;
        this.esActiva = esActiva;
        this.vehiculo = vehiculo;
        this.placaPersona = persona;
        this.setTextoPlaca();
    }

    public String getTextoPlaca() {
        return textoPlaca;
    }

    public final void setTextoPlaca() {
        this.textoPlaca = ManejadorPlacas.generarPlaca();
    }

    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Boolean getEsActiva() {
        return esActiva;
    }

    public void setEsActiva(Boolean esActiva) {
        this.esActiva = esActiva;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Persona getPlacaPersona() {
        return placaPersona;
    }

    public void setPlacaPersona(Persona placaPersona) {
        this.placaPersona = placaPersona;
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
        if (!(object instanceof Placa)) {
            return false;
        }
        Placa other = (Placa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Placa{" + "textoPlaca=" + textoPlaca + ", fechaRecepcion=" + fechaRecepcion + ", esActiva=" + esActiva + ", vehiculo=" + vehiculo + ", placaPersona=" + placaPersona + '}';
    }
}
