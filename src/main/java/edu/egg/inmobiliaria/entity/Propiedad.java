package edu.egg.inmobiliaria.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Propiedad {

    @Id
    @Column(name = "id", length = 100, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "precio", length = 100, nullable = false)
    private Double precio;

    @Column(name = "habitacion", length = 100, nullable = false)
    private Integer habitacion;

    @Column(name = "banos", length = 100, nullable = false)
    private Integer banos;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "estacionamiento", nullable = false)
    private Boolean estacionamiento;

    @Column(name = "patio", nullable = false)
    private Boolean patio;

    @ManyToOne
    @JoinColumn(name = "Usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    public Propiedad() {
    }

    public Propiedad(Integer id, String titulo, Double precio, Integer habitacion, Integer banos, String descripcion, Boolean estacionamiento, Boolean patio, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.habitacion = habitacion;
        this.banos = banos;
        this.descripcion = descripcion;
        this.estacionamiento = estacionamiento;
        this.patio = patio;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Integer habitacion) {
        this.habitacion = habitacion;
    }

    public Integer getBanos() {
        return banos;
    }

    public void setBanos(Integer banos) {
        this.banos = banos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
