package com.racasoft.cursito.controllers;

import com.racasoft.cursito.dao.UsuarioDao;
import com.racasoft.cursito.models.Usuario;
import com.racasoft.cursito.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioDao.obtenerYVerificarUsuario(usuario);

        if(usuarioLogueado != null) {

            String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());

            return tokenJWT;

        } else {
            return "No Pibe, mandaste cualquiera";
        }
    }

}
