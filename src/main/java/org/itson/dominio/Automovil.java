/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "automoviles")
public class Automovil extends Vehiculo implements Serializable {

    public Automovil() {
        super();
    }

    public Automovil(String serie, String marca, String linea, String modelo, String color) {
        super(serie, marca, linea, modelo, color);
    }

    public Automovil(Long id, String serie, String marca, String linea, String modelo, String color) {
        super(id, serie, marca, linea, modelo, color);
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Automovil)) {
            return false;
        }
        Automovil other = (Automovil) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "org.itson.dominio.Automovil[ id=" + id + " ]";
    }
    
}
