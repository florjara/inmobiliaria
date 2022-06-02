package edu.egg.inmobiliaria.service;

import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //crea un usuario
    @Transactional
    public void create (Usuario dto){

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(dto.getContrasena());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        usuarioRepository.save(usuario);
    }

    //actualiza un usuario
    @Transactional
    public void update (Usuario dto){

        Usuario usuario = usuarioRepository.findById(dto.getId()).get();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(dto.getContrasena());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        usuarioRepository.save(usuario);

    }

    //busca un usuario por id
    @Transactional(readOnly = true)
    public Usuario getById(Long id){
        return usuarioRepository.findById(id).get();
    }

    //devuelve la lista de todos los usuarios
    @Transactional(readOnly = true)
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    //habilita un usuario
    @Transactional
    public void enableById(Long id){
        usuarioRepository.getReferenceById(id);
    }

    //borra un usuario por id
    @Transactional
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

}
