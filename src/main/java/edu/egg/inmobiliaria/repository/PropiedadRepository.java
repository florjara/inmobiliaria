package edu.egg.inmobiliaria.repository;

import edu.egg.inmobiliaria.entity.Propiedad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    
    @Query("SELECT p FROM Propiedad p WHERE p.ciudad LIKE %?1% AND p.tipo LIKE %?2% AND p.tipoTransaccion LIKE %?3%" )
    public List<Propiedad> findAll (String ciudad, String tipo, String tipoTransaccion);
    
    @Query("SELECT p FROM Propiedad p WHERE (p.ciudad LIKE %?1% AND p.tipo LIKE %?2% AND p.tipoTransaccion LIKE %?3%) AND (p.precio BETWEEN ?4 AND ?5)" )
    public List<Propiedad> findAll (String ciudad, String tipo, String tipoTransaccion, Double min, Double max);
    
    @Query("SELECT p FROM Propiedad p WHERE p.usuario.id = ?1" )
    public List<Propiedad> obtenerPorIdUsuario (Long id);
    
}
