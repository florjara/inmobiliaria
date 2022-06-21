package edu.egg.inmobiliaria.controller;


import edu.egg.inmobiliaria.entity.PropiedadFiltro;
import edu.egg.inmobiliaria.enums.Ciudad;
import edu.egg.inmobiliaria.enums.TipoPropiedad;
import edu.egg.inmobiliaria.enums.Transaccion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PrincipalController {

    @GetMapping
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("prop", new PropiedadFiltro());
        mav.addObject("ciudades", Ciudad.values());
        mav.addObject("tipoPropiedad", TipoPropiedad.values());
        mav.addObject("transacciones", Transaccion.values());
        return mav;
    }
}
