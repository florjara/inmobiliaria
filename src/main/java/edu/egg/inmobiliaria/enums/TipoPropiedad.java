package edu.egg.inmobiliaria.enums;

public enum TipoPropiedad {

    CASA("Casa"), 
    DEPARTAMENTO("Departamento"),
    LOCAL("Local"),
    DUPLEX("Duplex"),
    OFICINA("Oficina");

    private String nombre;

    private TipoPropiedad() {
    }

    private TipoPropiedad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
