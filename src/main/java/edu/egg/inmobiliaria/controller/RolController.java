package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Rol;
import edu.egg.inmobiliaria.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getForm(){
        ModelAndView mav=new ModelAndView("rol-form");
        mav.addObject("rol", new Rol());

        return mav;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView create(Rol rolDto, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/");

        try {
            rolService.create(rolDto);
        } catch (IllegalArgumentException e) {
            atributos.addFlashAttribute("rol", rolDto);
            atributos.addFlashAttribute("excepcion", e.getMessage());
            redireccion.setUrl("/form");

        }
        return redireccion;
    }
}
