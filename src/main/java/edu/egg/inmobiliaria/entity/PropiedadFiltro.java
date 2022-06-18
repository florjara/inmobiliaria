package edu.egg.inmobiliaria.entity;

//import org.springframework.stereotype.Component;

//@Component
public class PropiedadFiltro {

    private Double precioMin;
    
    private Double precioMax;

    private Integer ambiente;

    private Integer banos;

    private String tipo;

    private String tipoTransaccion;

    private Boolean estacionamiento;

    private Boolean patio;

    private String ciudad;

    public PropiedadFiltro() {
    }

    public PropiedadFiltro(Double precioMin, Double precioMax, Integer ambiente, Integer banos, String tipo, String tipoTransaccion, Boolean estacionamiento, Boolean patio, String ciudad) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
   
}