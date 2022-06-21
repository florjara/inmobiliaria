package edu.egg.inmobiliaria.service;

import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.repository.RolRepository;
import edu.egg.inmobiliaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.function.Supplier;


import static java.util.Collections.singletonList;

@Service
public class UsuarioService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encriptador;
    private final RolRepository rolRepository;
    private ImageService imageService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encriptador, RolRepository rolRepository, ImageService imageService) {
        this.usuarioRepository = usuarioRepository;
        this.encriptador = encriptador;
        this.rolRepository = rolRepository;
        this.imageService = imageService;
    }


    //crea un usuario
    @Transactional
    public void create (Usuario dto, MultipartFile photo){

        if (usuarioRepository.existsByCorreo(dto.getCorreo()))
            throw new IllegalArgumentException("Este correo ya fue registrado con anterioridad.");

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(encriptador.encode(dto.getContrasena()));
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEliminado(Boolean.FALSE);

        if (!photo.isEmpty()) {
            usuario.setImage(imageService.copy(photo));
        }

        if (dto.getRol() == null)
            usuario.setRol(rolRepository.findByNombre("USUARIO").orElseThrow(() -> new IllegalArgumentException("Error")));

        usuarioRepository.save(usuario);
    }

    //actualiza un usuario
    @Transactional
    public void update (Usuario dto, MultipartFile photo){

        Usuario usuario = usuarioRepository.findById(dto.getId()).get();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setContrasena(encriptador.encode(dto.getContrasena()));
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());

        if (!photo.isEmpty()) {
            usuario.setImage(imageService.copy(photo));
        }

        usuarioRepository.save(usuario);

    }

    //busca un usuario por id
    @Transactional(readOnly = true)
    public Usuario getById(Long id) throws Exception {
        return usuarioRepository.findById(id).orElseThrow(()-> new Exception("Este usuario no existe."));
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

        GrantedAuthority autorizacion= new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre());


        ServletRequestAttributes atributos = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = atributos.getRequest().getSession(true);

        session.setAttribute("id", usuario.getId());
        session.setAttribute("nombre", usuario.getNombre());
        session.setAttribute("apellido", usuario.getApellido());
        session.setAttribute("correo", usuario.getCorreo());
        session.setAttribute("rol", usuario.getRol());
        
        session.setAttribute("telefono", usuario.getTelefono());

        return new User(usuario.getCorreo(), usuario.getContrasena(), singletonList(autorizacion));
    }
}
