package edu.egg.inmobiliaria.service;

import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.emptyList;

@Service
public class UsuarioService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encriptador;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encriptador) {
        this.usuarioRepository = usuarioRepository;
        this.encriptador = encriptador;
    }


    //crea un usuario
    @Transactional
    public void create (Usuario dto){

        if (usuarioRepository.existsByCorreo(dto.getCorreo()))
            throw new IllegalArgumentException("Este correo ya fue registrado con aterioridad");

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(encriptador.encode(dto.getContrasena()));
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEliminado(Boolean.FALSE);

        usuarioRepository.save(usuario);
    }

    //actualiza un usuario
    @Transactional
    public void update (Usuario dto){

        Usuario usuario = usuarioRepository.findById(dto.getId()).get();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(encriptador.encode(dto.getContrasena()));
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

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException{
        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException("No existe un usuario registrado con este correo");
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow(supplier);

        return new User(usuario.getCorreo(), usuario.getContrasena(), emptyList());
    }
}
