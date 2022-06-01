package edu.egg.inmobiliaria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "correo", length = 100, nullable = false)
    private String correo;

    @Column(name = "telefono", nullable = false)
    private Long telefono;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "eliminado", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean eliminado;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String correo, Long telefono, String contrasena, Boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.eliminado = eliminado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
