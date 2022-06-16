package edu.egg.inmobiliaria.service;

import edu.egg.inmobiliaria.entity.Rol;
import edu.egg.inmobiliaria.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService {
    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Transactional
    public void create(Rol roldto){
        if(rolRepository.existsByNombre(roldto.getNombre())) throw new IllegalArgumentException("Error: este rol ya fue creado.");
        Rol rol = new Rol();
        rol.setNombre(roldto.getNombre());
        rol.setEliminado(Boolean.FALSE);
        rolRepository.save(rol);
    }

    @Transactional(readOnly = true)
    public List<Rol> getAll(){
        return rolRepository.findAll();
    }
}
