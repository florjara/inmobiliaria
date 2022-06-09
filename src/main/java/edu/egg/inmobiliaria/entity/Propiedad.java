package edu.egg.inmobiliaria.entity;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "propiedad", indexes = {
    @Index(name = "idx_titulo", columnList = "titulo")})
@SQLDelete(sql = "UPDATE propiedad SET eliminado = true WHERE id = ?")
public class Propiedad {

    @Id
    @Column(name = "id", length = 100, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "precio", length = 100, nullable = false)
    private Double precio;

    @Column(name = "ambiente", length = 100)
    private Integer ambiente;

    @Column(name = "banos", length = 100)
    private Integer banos;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "tipo_transaccion", nullable = false)
    private String tipoTransaccion;

    @Column(name = "estacionamiento", columnDefinition = "BOOLEAN")
    private Boolean estacionamiento;

    @Column(name = "patio", columnDefinition = "BOOLEAN")
    private Boolean patio;

    @Column(name = "eliminado",  columnDefinition = "BOOLEAN")
    private Boolean eliminado;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "image")
    private String image;

    @Column(name = "Ciudad")
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "Usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Propiedad() {
    }

    public Propiedad(Long id, String titulo, Double precio, Integer ambiente, Integer banos, String descripcion, String tipo, String tipoTransaccion, Boolean estacionamiento, Boolean patio, Boolean eliminado, String direccion, String image, String ciudad, Usuario usuario) {
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
        this.eliminado = eliminado;
        this.direccion = direccion;
        this.image = image;
        this.ciudad = ciudad;
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

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
