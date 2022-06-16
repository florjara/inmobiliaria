package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Propiedad;

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
import javax.servlet.http.HttpSession;
import org.springframework.data.repository.query.Param;
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
    public ModelAndView getAllPropiedades(HttpServletRequest request, @Param("ciudad") String ciudad, @Param("tipo") String tipo, @Param("tipoTransaccion") String tipoTransaccion, @Param("min") Double min, @Param("max") Double max) {
        ModelAndView mav = new ModelAndView("listado_propiedades");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }
       
        mav.addObject("propiedades", propiedadService.getAll(ciudad, tipo, tipoTransaccion, min, max));

        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/form")
    public ModelAndView formCrearPropiedad() {
        ModelAndView mav = new ModelAndView("form_propiedad");
        mav.addObject("propiedad", new Propiedad());
        mav.addObject("usuarios", usuarioService.getAll());
        mav.addObject("action", "crear");
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/form/{id}")
    public ModelAndView formActualizarPropiedad(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form_propiedad");
        mav.addObject("usuarios", usuarioService.getAll());
        mav.addObject("propiedad", propiedadService.getById(id));
        mav.addObject("action", "actualizar");
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/{id}")
    public ModelAndView obtenerPropiedad(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("propiedad");
        mav.addObject("propiedad", propiedadService.getById(id));
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/crear")
    public RedirectView crear(Propiedad propiedadDto, @RequestParam(required = false) List<MultipartFile> photo, RedirectAttributes attributes, HttpSession session) {
        RedirectView redirect = new RedirectView("/propiedades");

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
        RedirectView redirect = new RedirectView("/propiedades");
        propiedadService.update(propiedadDto, photo);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/propiedades");
        propiedadService.deleteById(id);
        return redirect;
    }

}
