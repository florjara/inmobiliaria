package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Propiedad;
import edu.egg.inmobiliaria.entity.Usuario;
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
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
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
    public ModelAndView getAllPropiedades(HttpServletRequest request, @Param("ciudad") String ciudad, @Param("tipo") String tipo, @Param("tipoTransaccion") String tipoTransaccion) {
        ModelAndView mav = new ModelAndView("listado_propiedades");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }

        mav.addObject("propiedades", propiedadService.getAll(ciudad, tipo, tipoTransaccion));

        return mav;
    }

//    @GetMapping
//    public ModelAndView getBooks(HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("index");
//        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
//
//        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));
//
//        mav.addObject("propiedades", propiedadService.getAll(ciudad, tipo, tipoTransaccion));
//        return mav;
//    }
    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form_propiedad");
        mav.addObject("propiedad", new Propiedad());
        mav.addObject("usuarios", usuarioService.getAll());
        mav.addObject("action", "crear");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form_propiedad");
        mav.addObject("usuarios", usuarioService.getAll());
        mav.addObject("propiedad", propiedadService.getById(id));
        mav.addObject("action", "actualizar");
        return mav;
    }

    @PostMapping("/crear")
    public RedirectView crear(Propiedad propiedadDto, @RequestParam(required = false) MultipartFile photo , RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/propiedades");

        try{
            propiedadService.crear(propiedadDto, photo);
            attributes.addFlashAttribute("success", "La operacion ha sido exitosa");
        }catch (IllegalArgumentException e){
            attributes.addFlashAttribute("author", propiedadDto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/propiedades/form");
        }

        return redirect;
    }

    @PostMapping("/actualizar")
    public RedirectView actualizar(Propiedad propiedadDto, @RequestParam(required = false) MultipartFile photo, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/propiedades");
        propiedadService.update(propiedadDto, photo);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/propiedades");
        propiedadService.deleteById(id);
        return redirect;
    }

}
