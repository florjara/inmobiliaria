package edu.egg.inmobiliaria.repository;

import edu.egg.inmobiliaria.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    boolean existsByNombre(String nombre);


    Optional<Rol> findByNombre(String nombre);
}
