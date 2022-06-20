package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.PropiedadFiltro;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {

    @GetMapping
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("prop", new PropiedadFiltro());
        return mav;
    }
}
