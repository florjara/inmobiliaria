package edu.egg.inmobiliaria.service;

import edu.egg.inmobiliaria.entity.Propiedad;
import edu.egg.inmobiliaria.entity.PropiedadFiltro;
import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.enums.Ciudad;
import edu.egg.inmobiliaria.repository.PropiedadRepository;
import edu.egg.inmobiliaria.repository.UsuarioRepository;
import java.util.ArrayList;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PropiedadService {

    private final EntityManager propiedadManager;

    private final PropiedadRepository propiedadRepository;
    private final UsuarioRepository usuarioRepository;
    private ImageService imageService;

    @Autowired
    public PropiedadService(EntityManager propiedadManager, PropiedadRepository propiedadRepository, UsuarioRepository usuarioRepository, ImageService imageService) {
        this.propiedadManager = propiedadManager;
        this.propiedadRepository = propiedadRepository;
        this.usuarioRepository = usuarioRepository;
        this.imageService = imageService;
    }

    @Transactional
    public void crear(Propiedad dto, List<MultipartFile> listaFotos, HttpSession session) {

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
        propiedad.setEliminado(Boolean.FALSE);
        propiedad.setDireccion(dto.getDireccion());

        if (!listaFotos.get(0).getOriginalFilename().isEmpty()) {
            List<String> lista = new ArrayList<>();
            if (!listaFotos.isEmpty()) {

                for (MultipartFile foto : listaFotos) {
                    lista.add(imageService.copy(foto));
                    propiedad.setImage(lista);
                }
            }
        }

        Usuario usuario = usuarioRepository.findById((Long) session.getAttribute("id")).get();
        propiedad.setUsuario(usuario);
        propiedadRepository.save(propiedad);

    }

    @Transactional
    public void update(Propiedad dto, List<MultipartFile> listaFotos) {

        Propiedad propiedad = propiedadRepository.findById(dto.getId()).get();

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
        propiedad.setEliminado(Boolean.FALSE);
        propiedad.setDireccion(dto.getDireccion());

        if (!listaFotos.get(0).getOriginalFilename().isEmpty()) {
            List<String> lista = new ArrayList<>();
            if (!listaFotos.isEmpty()) {

                for (MultipartFile foto : listaFotos) {
                    lista.add(imageService.copy(foto));
                    propiedad.setImage(lista);
                }
            }
        }

        propiedadRepository.save(propiedad);
    }

    @Transactional(readOnly = true)
    public Propiedad getById(Long id) {
        return propiedadRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Propiedad> getAll(PropiedadFiltro p) {
        if (p.getTipo() == null && p.getTipoTransaccion() == null) {
            return propiedadRepository.findAll();
        }

        Optional<Ciudad> ciudadOpt = Optional.ofNullable(p.getCiudad());
        Optional<Double> minOpt = Optional.ofNullable(p.getPrecioMin());
        Optional<Double> maxOpt = Optional.ofNullable(p.getPrecioMax());
        Optional<Integer> ambienteOpt = Optional.ofNullable(p.getAmbiente());
        Optional<Integer> banosOpt = Optional.ofNullable(p.getBanos());

       if (minOpt.isPresent() || maxOpt.isPresent()|| banosOpt.isPresent() || ambienteOpt.isPresent()) {
            return propiedadRepository.findAll(ciudadOpt.get(),
                    p.getTipo(), p.getTipoTransaccion(), minOpt.orElse(0d), maxOpt.orElse(Double.MAX_VALUE),
                    banosOpt.orElse(0), ambienteOpt.orElse(0));
        }

        return propiedadRepository.findAll(ciudadOpt.get(), p.getTipo(), p.getTipoTransaccion());
    }

    @Transactional(readOnly = true)
    public List<Propiedad> getAll(PropiedadFiltro p, Boolean eliminado) {
        Filter filtro =  propiedadManager.unwrap(Session.class).enableFilter("filtroPropiedadEliminada");
        filtro.setParameter("eliminado", eliminado);
        if (p.getTipo() == null && p.getTipoTransaccion() == null) {
            List<Propiedad> propiedadesDisponibles = propiedadRepository.findAll();
            propiedadManager.unwrap(Session.class).disableFilter("filtroPropiedadEliminada");
             return propiedadesDisponibles;
        }
        Optional<Ciudad> ciudadOpt = Optional.ofNullable(p.getCiudad());
        Optional<Double> minOpt = Optional.ofNullable(p.getPrecioMin());
        Optional<Double> maxOpt = Optional.ofNullable(p.getPrecioMax());
        Optional<Integer> ambienteOpt = Optional.ofNullable(p.getAmbiente());
        Optional<Integer> banosOpt = Optional.ofNullable(p.getBanos());

        if (minOpt.isPresent() || maxOpt.isPresent()|| banosOpt.isPresent() || ambienteOpt.isPresent()) {
            List<Propiedad> propiedadesDisponibles = propiedadRepository.findAll(ciudadOpt.get(),
                    p.getTipo(), p.getTipoTransaccion(), minOpt.orElse(0d), maxOpt.orElse(Double.MAX_VALUE),
                    banosOpt.orElse(0), ambienteOpt.orElse(0));
            return propiedadesDisponibles;
        }

        List<Propiedad> propiedadesDisponibles = propiedadRepository.findAll(ciudadOpt.get(), p.getTipo(), p.getTipoTransaccion());
        propiedadManager.unwrap(Session.class).disableFilter("filtroPropiedadEliminada");

        return propiedadesDisponibles;
    }

    @Transactional
    public void deleteById(Long id) {
        propiedadRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> obtenerPorIdUsuario(Long id) {
        return propiedadRepository.obtenerPorIdUsuario(id);
    }

    @Transactional(readOnly = true)
    public List<Propiedad> obtenerPorIdUsuario(Long id, Boolean eliminado) {
        Filter filtro =  propiedadManager.unwrap(Session.class).enableFilter("filtroPropiedadEliminada");
        filtro.setParameter("eliminado", eliminado);
        List<Propiedad> propiedadesDisponibles = propiedadRepository.obtenerPorIdUsuario(id);
        propiedadManager.unwrap(Session.class).disableFilter("filtroPropiedadEliminada");

        return propiedadesDisponibles;
    }
}
