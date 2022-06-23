package edu.egg.inmobiliaria.enums;

public enum Ciudad {
    
    POSADAS("Posadas"),
    OBERA("Obera"),
    APOSTOLES("Apostoles"),
    ELDORADO("Eldorado"),
    ARISTOBULO("Aristobulo del Valle");

    private String nombre;

    private Ciudad() {
    }

    private Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
