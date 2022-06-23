package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Propiedad;
import edu.egg.inmobiliaria.entity.PropiedadFiltro;
import edu.egg.inmobiliaria.enums.Ciudad;
import edu.egg.inmobiliaria.enums.TipoPropiedad;
import edu.egg.inmobiliaria.enums.Transaccion;

import edu.egg.inmobiliaria.service.PropiedadService;
import edu.egg.inmobiliaria.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping//("/lista-propiedades")
    public ModelAndView getAllPropiedades(HttpServletRequest request, @RequestParam(value = "eliminado", required = false, defaultValue = "false") boolean eliminado) {

        ModelAndView mav = new ModelAndView("listado_propiedades");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
            if (inputFlashMap.containsKey("prop")) {
                mav.addObject("prop", inputFlashMap.get("prop"));
                mav.addObject("propiedades", inputFlashMap.get("propiedades"));
            } else {
                PropiedadFiltro p = new PropiedadFiltro();
                mav.addObject("prop", p);
                mav.addObject("propiedades", propiedadService.getAll(p, eliminado));
            }

        } else {
            PropiedadFiltro p = new PropiedadFiltro();
            mav.addObject("prop", p);
            mav.addObject("propiedades", propiedadService.getAll(p, eliminado));
        }

        mav.addObject("ciudades", Ciudad.values());
        mav.addObject("tipoPropiedad", TipoPropiedad.values());
        mav.addObject("transacciones", Transaccion.values());

        return mav;
    }

    @PostMapping("/filtro")
    public RedirectView filtrarPropiedades(PropiedadFiltro propiedad, RedirectAttributes attributes, @RequestParam(value = "eliminado", required = false, defaultValue = "false") boolean eliminado) {
        RedirectView rv = new RedirectView("/propiedades");

        attributes.addFlashAttribute("prop", propiedad);
        attributes.addFlashAttribute("propiedades", propiedadService.getAll(propiedad,eliminado));

        return rv;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/form")
    public ModelAndView formCrearPropiedad() {
        ModelAndView mav = new ModelAndView("form_propiedad");
        mav.addObject("propiedad", new Propiedad());
        mav.addObject("usuarios", usuarioService.getUsuariosDisponibles());
        mav.addObject("boton", "crear");
        mav.addObject("action", "crear");
        mav.addObject("ciudades", Ciudad.values());
        mav.addObject("tipoPropiedad", TipoPropiedad.values());
        mav.addObject("transacciones", Transaccion.values());
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/form/{id}")
    public ModelAndView formActualizarPropiedad(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form_propiedad");
        mav.addObject("usuarios", usuarioService.getUsuariosDisponibles());
        mav.addObject("propiedad", propiedadService.getById(id));
        mav.addObject("boton", "Actualizar");

        mav.addObject("action", "actualizar");

        mav.addObject("ciudades", Ciudad.values());
        mav.addObject("tipoPropiedad", TipoPropiedad.values());
        mav.addObject("transacciones", Transaccion.values());

        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView obtenerPropiedad(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("propiedad");
        mav.addObject("propiedad", propiedadService.getById(id));
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/crear")
    public RedirectView crear(Propiedad propiedadDto, @RequestParam(required = false) List<MultipartFile> photo, RedirectAttributes attributes, HttpSession session) {
        RedirectView redirect = new RedirectView("/usuarios/perfil");

        try {
            propiedadService.crear(propiedadDto, photo, session);
            attributes.addFlashAttribute("success", "La operacion ha sido exitosa");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("propiedad", propiedadDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/propiedades/form");
        }

        return redirect;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/actualizar")
    public RedirectView actualizar(Propiedad propiedadDto, @RequestParam(required = false) List<MultipartFile> photo, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/usuarios/perfil");
        propiedadService.update(propiedadDto, photo);
        attributes.addFlashAttribute("success", "La propiedad se actualizo correctamente.");
        return redirect;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/usuarios/perfil");
        propiedadService.deleteById(id);
        attributes.addFlashAttribute("success", "La propiedad se elimino correctamente.");
        return redirect;
    }

}
