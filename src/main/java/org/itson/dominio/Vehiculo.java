/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "vehiculos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo")
public abstract class Vehiculo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "serie", nullable = false, length = 15)
    protected String serie;
    
    @Column(name = "marca", nullable = false, length = 30)
    protected String marca;
    
    @Column(name = "linea", nullable = false, length = 30)
    protected String linea;
    
    @Column(name = "modelo", nullable = false, length = 30)
    protected String modelo;
    
    @Column(name = "color", nullable = false, length = 30)
    protected String color;
    
    @OneToMany(mappedBy = "vehiculo")
    protected List<Placa> placas;

    public Vehiculo() {
    }

    public Vehiculo(String serie, String marca, String linea, String modelo, String color) {
        this.serie = serie;
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.color = color;
    }

    public Vehiculo(Long id, String serie, String marca, String linea, String modelo, String color) {
        this.id = id;
        this.serie = serie;
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.color = color;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Placa> getPlacas() {
        return placas;
    }

    public void setPlacas(List<Placa> placas) {
        this.placas = placas;
    }
    
    public void addPlaca(Placa placa) {
        if (this.placas == null) {
            this.placas = new ArrayList<>();
        }
        
        this.placas.add(placa);
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
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", serie=" + serie + ", marca=" + marca + ", linea=" + linea + ", modelo=" + modelo + ", color=" + color + '}';
    }
}
