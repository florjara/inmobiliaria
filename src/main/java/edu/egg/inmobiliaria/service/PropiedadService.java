package edu.egg.inmobiliaria.service;

import edu.egg.inmobiliaria.entity.Propiedad;
import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.repository.PropiedadRepository;
import edu.egg.inmobiliaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;
    private final UsuarioRepository usuarioRepository; //linea modificada para poner el usuario por defecto en 1
    private ImageService imageService;

    @Autowired
    public PropiedadService(PropiedadRepository propiedadRepository, UsuarioRepository usuarioRepository, ImageService imageService) {
        this.propiedadRepository = propiedadRepository;
        this.usuarioRepository = usuarioRepository;
        this.imageService = imageService;
    }

    @Transactional
    public void crear(Propiedad dto, MultipartFile photo) {

        Propiedad propiedad = new Propiedad();

        propiedad.setTitulo(dto.getTitulo());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setPrecio(dto.getPrecio());
        propiedad.setAmbiente(dto.getAmbiente());
        propiedad.setBanos(dto.getBanos());
        propiedad.setPatio(dto.getPatio());
        propiedad.setEstacionamiento(dto.getEstacionamiento());
        propiedad.setDireccion(dto.getDireccion());
        propiedad.setTipo(dto.getTipo());
        propiedad.setTipoTransaccion(dto.getTipoTransaccion());
        propiedad.setCiudad(dto.getCiudad());

        if (!photo.isEmpty()) {
            propiedad.setImage(imageService.copy(photo));
        }

        Usuario usuario = usuarioRepository.findById(1L).get(); //linea modificada para poner el usuario por defecto en 1
        propiedad.setUsuario(usuario); //linea modificada para poner el usuario por defecto en 1
        propiedadRepository.save(propiedad);

    }

    @Transactional
    public void update(Propiedad dto, MultipartFile photo) {

        Propiedad propiedad = propiedadRepository.findById(dto.getId()).get();

        propiedad.setTitulo(dto.getTitulo());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setPrecio(dto.getPrecio());
        propiedad.setUsuario(dto.getUsuario());
        propiedad.setAmbiente(dto.getAmbiente());
        propiedad.setBanos(dto.getBanos());
        propiedad.setPatio(dto.getPatio());
        propiedad.setEstacionamiento(dto.getEstacionamiento());
        propiedad.setDireccion(dto.getDireccion());
        propiedad.setTipo(dto.getTipo());
        propiedad.setTipoTransaccion(dto.getTipoTransaccion());
        propiedad.setCiudad(dto.getCiudad());

        if (!photo.isEmpty()) {
            propiedad.setImage(imageService.copy(photo));
        }

        propiedadRepository.save(propiedad);
    }


    @Transactional(readOnly = true)
    public Propiedad getById(Long id) {
        return propiedadRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> getAll(String ciudad, String tipo, String tipoTransaccion) {
        if (ciudad != null || tipo != null || tipoTransaccion != null) {
            return propiedadRepository.findAll(ciudad, tipo, tipoTransaccion);
        }
        return propiedadRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        propiedadRepository.deleteById(id);
    }

}
