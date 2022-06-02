package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.service.UsuarioService;
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

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView getUsuarios (HttpServletRequest request){
        ModelAndView mav = new ModelAndView("usuario-tabla");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("usuarios", usuarioService.getAll());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView getForm(){
        ModelAndView mav = new ModelAndView("usuario-form");
        mav.addObject("usuario", new Usuario());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("usuario-form");
        mav.addObject("usuario", usuarioService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Usuario usuarioDto, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioService.create(usuarioDto);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Usuario usuarioDto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioService.update(usuarioDto);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioService.deleteById(id);
        return redirect;
    }

}
