package edu.egg.inmobiliaria.enums;

public enum Transaccion {

    ALQUILER("Alquiler"),
    VENTA("Venta"),
    RESERVADO("Reservado"),
    NODISPONIBLE("No Disponible");

    private String nombre;

    private Transaccion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
