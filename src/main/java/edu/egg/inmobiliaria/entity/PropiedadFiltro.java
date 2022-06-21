package edu.egg.inmobiliaria.entity;

import org.springframework.stereotype.Component;

import edu.egg.inmobiliaria.enums.Ciudad;
import edu.egg.inmobiliaria.enums.TipoPropiedad;
import edu.egg.inmobiliaria.enums.Transaccion;

@Component
public class PropiedadFiltro {

    private Double precioMin;
    
    private Double precioMax;

    private Integer ambiente;

    private Integer banos;

    private TipoPropiedad tipo;

    private Transaccion tipoTransaccion;

    private Boolean estacionamiento;

    private Boolean patio;

    private Ciudad ciudad;

    public PropiedadFiltro() {
    }

    public PropiedadFiltro(Double precioMin, Double precioMax, Integer ambiente, Integer banos, TipoPropiedad tipo, Transaccion tipoTransaccion, Boolean estacionamiento, Boolean patio, Ciudad ciudad) {
        this.precioMin = precioMin;
        this.precioMax = precioMax;
        this.ambiente = ambiente;
        this.banos = banos;
        this.tipo = tipo;
        this.tipoTransaccion = tipoTransaccion;
        this.estacionamiento = estacionamiento;
        this.patio = patio;
        this.ciudad = ciudad;
    }

    public Double getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Double precioMin) {
        this.precioMin = precioMin;
    }

    public Double getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Double precioMax) {
        this.precioMax = precioMax;
    }

    public Integer getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Integer ambiente) {
        this.ambiente = ambiente;
    }

    public Integer getBanos() {
        return banos;
    }

    public void setBanos(Integer banos) {
        this.banos = banos;
    }

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }

    public Transaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(Transaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Boolean getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Boolean estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
   
}