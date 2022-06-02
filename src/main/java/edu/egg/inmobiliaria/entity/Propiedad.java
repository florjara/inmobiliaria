package edu.egg.inmobiliaria.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Propiedad {

    @Id
    @Column(name = "id", length = 100, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "precio", length = 100, nullable = false)
    private Double precio;

    @Column(name = "ambiente", length = 100, nullable = false)
    private Integer ambiente;

    @Column(name = "banos", length = 100, nullable = false)
    private Integer banos;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "tipo_transaccion", nullable = false)
    private String tipoTransaccion;

    @Column(name = "estacionamiento", columnDefinition = "BOOLEAN")
    private Boolean estacionamiento;

    @Column(name = "patio",columnDefinition = "BOOLEAN" )
    private Boolean patio;

    //@Column(name = "eliminado", nullable = false, columnDefinition = "BOOLEAN")
    //private Boolean eliminado; agregar despues

    @Column(name = "direccion")
    private String direccion;

    //@Column(name = "imagen")
    //private String imagen;

    @ManyToOne
    @JoinColumn(name = "Usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Propiedad() {
    }

    public Propiedad(Long id, String titulo, Double precio, Integer ambiente, Integer banos, String descripcion, String tipo, String tipoTransaccion, Boolean estacionamiento, Boolean patio, String direccion, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.ambiente = ambiente;
        this.banos = banos;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.tipoTransaccion = tipoTransaccion;
        this.estacionamiento = estacionamiento;
        this.patio = patio;
        this.direccion = direccion;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Usuario getUsuario() {
        return usuario;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
