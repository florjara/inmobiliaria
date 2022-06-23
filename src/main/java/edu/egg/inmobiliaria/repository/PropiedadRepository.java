package edu.egg.inmobiliaria.repository;

import edu.egg.inmobiliaria.entity.Propiedad;
import edu.egg.inmobiliaria.enums.Ciudad;
import edu.egg.inmobiliaria.enums.TipoPropiedad;
import edu.egg.inmobiliaria.enums.Transaccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

    @Query("SELECT p FROM Propiedad p")
    public List<Propiedad> findAll();

    @Query("SELECT p FROM Propiedad p WHERE p.ciudad LIKE ?1 AND p.tipo LIKE ?2 AND p.tipoTransaccion LIKE ?3")
    public List<Propiedad> findAll(Ciudad ciudad, TipoPropiedad tipo, Transaccion tipoTransaccion);

    @Query("SELECT p FROM Propiedad p WHERE (p.ciudad LIKE ?1 AND p.tipo LIKE ?2 AND p.tipoTransaccion LIKE ?3) AND (p.precio BETWEEN ?4 AND ?5) AND (p.banos >= ?6) AND (p.ambiente >= ?7)")
    public List<Propiedad> findAll(Ciudad ciudad, TipoPropiedad tipo, Transaccion tipoTransaccion, Double min, Double max, Integer banos, Integer ambiente);

    @Query("SELECT p FROM Propiedad p WHERE p.usuario.id = ?1")
    public List<Propiedad> obtenerPorIdUsuario(Long id);

}
