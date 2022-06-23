package edu.egg.inmobiliaria.entity;

import edu.egg.inmobiliaria.enums.Ciudad;
import edu.egg.inmobiliaria.enums.TipoPropiedad;
import edu.egg.inmobiliaria.enums.Transaccion;
import java.util.List;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "propiedad", indexes = {
    @Index(name = "idx_titulo", columnList = "titulo")})
@SQLDelete(sql = "UPDATE propiedad SET eliminado = true WHERE id = ?")
@FilterDef(name = "filtroPropiedadEliminada", parameters = @ParamDef(name = "eliminado", type = "boolean"))
@Filters(@Filter(name = "filtroPropiedadEliminada", condition = "eliminado = :eliminado"))
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

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPropiedad tipo;

    @Column(name = "tipo_transaccion", nullable = false)
    @Enumerated(EnumType.STRING)
    private Transaccion tipoTransaccion;

    @Column(name = "estacionamiento", columnDefinition = "BOOLEAN")
    private Boolean estacionamiento;

    @Column(name = "patio", columnDefinition = "BOOLEAN")
    private Boolean patio;

    @Column(name = "eliminado",  columnDefinition = "BOOLEAN")
    private Boolean eliminado;

    @Column(name = "direccion")
    private String direccion;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "image")
    private List<String> image;

    @Column(name = "Ciudad")
    @Enumerated(EnumType.STRING)
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "Usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Propiedad() {
    }

    public Propiedad(Long id, String titulo, Double precio, Integer ambiente, Integer banos, String descripcion, TipoPropiedad tipo, Transaccion tipoTransaccion, Boolean estacionamiento, Boolean patio, Boolean eliminado, String direccion, List<String> image, Ciudad ciudad, Usuario usuario) {
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

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
