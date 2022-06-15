package edu.egg.inmobiliaria.entity;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Table(name = "rol")
@SQLDelete(sql = "UPDATE rol SET eliminado = true WHERE id = ?")
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rol_id")
    private Long id;

    @Column(name = "rol_nombre", length = 30, unique = true ,nullable = false)
    private String nombre;

    @Column(name = "eliminado", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean eliminado;

    public Rol() {
    }

    public Rol(Long id, String nombre, Boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
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

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}
