package edu.egg.inmobiliaria.security;

import edu.egg.inmobiliaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {

    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder encriptador;

    @Autowired
    public AppSecurity(UsuarioService usuarioService, BCryptPasswordEncoder encriptador) {
        this.usuarioService = usuarioService;
        this.encriptador = encriptador;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(encriptador);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()

                    .antMatchers("/usuarios/sign-up", "/propiedades/{id}", "/propiedades","/imagenes/*","/usuarios/registrar", "/","/css/*", "/js/*").permitAll()
                    .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                    .loginPage("/usuarios/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("correo")
                        .passwordParameter("contrasena")
                        .defaultSuccessUrl("/",true)
                        .failureUrl("/usuarios/login?error=true")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=true")
                        .permitAll()
                .and()
                    .csrf()
                    .disable();
        // @formatter:on
    }
}
