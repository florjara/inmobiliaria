package edu.egg.inmobiliaria.controller;

import edu.egg.inmobiliaria.entity.Usuario;
import edu.egg.inmobiliaria.service.PropiedadService;
import edu.egg.inmobiliaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PropiedadService propiedadService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, PropiedadService propiedadService) {
        this.usuarioService = usuarioService;
        this.propiedadService = propiedadService;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("login_usuario");

        if (error != null) {
            mav.addObject("error", "Correo o contraseña erroneos");
        }
        if (logout != null) {
            mav.addObject("logout", "Saliste satisfactoriamente de la sesion");
        }
        if (principal != null) {
            mav.setViewName("redirect:/");
        }

        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/todoslosusuarios")
    public ModelAndView getUsuarios(HttpServletRequest request, @RequestParam(value = "eliminado", required = false, defaultValue = "false") boolean eliminado){
        ModelAndView mav = new ModelAndView("lista_usuarios"); // armar html con tabla para q el admin vea los usuarios
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }

        mav.addObject("usuarios", usuarioService.getUsuariosDisponibles(eliminado));
        return mav;
    }
/*
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/todoslosusuarios/{eliminado}")
    public ModelAndView getUsuarios(HttpServletRequest request, @PathVariable Boolean eliminado) {
        ModelAndView mav = new ModelAndView("lista_usuarios"); // armar html con tabla para q el admin vea los usuarios
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("success", inputFlashMap.get("success"));
        }

        mav.addObject("usuarios", usuarioService.getUsuariosDisponibles(eliminado));
        return mav;
    }
    */
   
    @GetMapping("/sign-up")
    public ModelAndView signup(HttpServletRequest solicitud, Principal principal) {
        ModelAndView mav = new ModelAndView("form_usuario");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(solicitud);

        if (principal != null) {
            mav.setViewName("redirect:/");
        }

        if (inputFlashMap != null) {
            mav.addObject("excepcion", inputFlashMap.get("excepcion"));
            mav.addObject("usuario", inputFlashMap.get("usuario"));
        } else {
            mav.addObject("usuario", new Usuario());
            mav.addObject("action", "registrar");
        }
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/form/{id}")
    public ModelAndView formActualizar(@PathVariable Long id, HttpSession session) throws Exception {
        if (!session.getAttribute("id").equals(id)) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView mav = new ModelAndView("form_usuario");
        mav.addObject("usuario", usuarioService.getById(id));
        mav.addObject("action", "actualizar");
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/perfil")
    public ModelAndView obtenerPerfilUsuario(HttpSession session, @RequestParam(value = "eliminado", required = false, defaultValue = "false") boolean eliminado) throws Exception {
        ModelAndView mav = new ModelAndView("perfil");
        Long idUsuario = (Long) session.getAttribute("id");
        mav.addObject("usuario", usuarioService.getById(idUsuario));
        mav.addObject("propiedades", propiedadService.obtenerPorIdUsuario(idUsuario, eliminado));
        return mav;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @GetMapping("/perfil/{id}")
    public ModelAndView obtenerUsuarioPorId(@PathVariable Long id, @RequestParam(value = "eliminado", required = false, defaultValue = "false") boolean eliminado) throws Exception {
        ModelAndView mav = new ModelAndView("perfil");
        mav.addObject("usuario", usuarioService.getById(id));
        mav.addObject("propiedades", propiedadService.obtenerPorIdUsuario(id, eliminado));
        return mav;
    }

    @PostMapping("/registrar")
    public RedirectView signup(Usuario usuarioDto,@RequestParam(required = false) MultipartFile photo ,RedirectAttributes atributos) {
        RedirectView redireccion = new RedirectView("/login");

        try {
            usuarioService.create(usuarioDto, photo );
        } catch (IllegalArgumentException e) {
            atributos.addFlashAttribute("usuario", usuarioDto);
            atributos.addFlashAttribute("excepcion", e.getMessage());
            redireccion.setUrl("/usuarios/sign-up");
        }

        return redireccion;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/actualizar")

    public RedirectView actualizar(Usuario usuarioDto, @RequestParam(required = false) MultipartFile photo,RedirectAttributes attributes) {
        
        RedirectView redirect = new RedirectView("/usuarios/perfil");
        usuarioService.update(usuarioDto,photo);

        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id, HttpSession session) {
        if (!session.getAttribute("id").equals(id)) {
            return new RedirectView("/");
        }
        RedirectView redirect = new RedirectView("/usuarios");
        usuarioService.deleteById(id);
        return redirect;
    }

}
