package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController{

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("login_usuario");

        if (error != null) mav.addObject("error", "Correo o contraseña erroneos");
        if (logout != null) mav.addObject("logout", "Saliste satisfactoriamente de la sesion");
        if (principal != null) mav.setViewName("redirect:/");


        return mav;
    }

    @GetMapping
    public ModelAndView getUsuarios(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("index");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("usuarios", usuarioService.getAll());
        return mav;
    }

    @GetMapping("/sign-up")
    public ModelAndView signup(HttpServletRequest solicitud, Principal principal) {
        ModelAndView mav = new ModelAndView("form_usuario");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(solicitud);

        if (principal != null) mav.setViewName("redirect:/");

        if (inputFlashMap != null) {
            mav.addObject("excepcion", inputFlashMap.get("excepcion"));
            mav.addObject("usuario", inputFlashMap.get("usuario"));
        } else {
            mav.addObject("usuario", new Usuario());
            mav.addObject("action", "registrar");
        }
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form_usuario");
        mav.addObject("usuario", usuarioService.getById(id));
        mav.addObject("action", "actualizar");
        return mav;
    }


    @PostMapping("/registrar")
    public RedirectView signup(Usuario usuarioDto, RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/login");


        try {
            usuarioService.create(usuarioDto);
        } catch (IllegalArgumentException e) {
            atributos.addFlashAttribute("usuario", usuarioDto);
            atributos.addFlashAttribute("excepcion", e.getMessage());
            redireccion.setUrl("/usuarios/sign-up");
        }

        return redireccion;
    }

    @PostMapping("/actualizar")
    public RedirectView actualizar(Usuario usuarioDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioService.update(usuarioDto);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }


    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioService.deleteById(id);
        return redirect;
    }

}
