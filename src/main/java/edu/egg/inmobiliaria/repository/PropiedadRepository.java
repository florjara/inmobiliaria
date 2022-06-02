package edu.egg.inmobiliaria.repository;

import edu.egg.inmobiliaria.entity.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
}
