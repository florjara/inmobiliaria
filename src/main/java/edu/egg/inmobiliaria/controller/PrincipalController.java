package edu.egg.inmobiliaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PrincipalController {

    @GetMapping
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
