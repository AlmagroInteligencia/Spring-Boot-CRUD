package com.racasoft.cursito.controllers;

import com.racasoft.cursito.dao.UsuarioDao;
import com.racasoft.cursito.models.Usuario;
import com.racasoft.cursito.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable long id) {
        Usuario usuario01 = new Usuario();
        usuario01.setId(id);
        usuario01.setNombre("Cosme");
        usuario01.setApellido("Fulanitos");
        usuario01.setEmail("cosme_fulanitos@mailfalso.com");
        usuario01.setTelefono("0303456");
        usuario01.setPassword("refacil");
        return usuario01;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        if(!validarToken(token)) {
            return null;
        }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String idUsuario = jwtUtil.getKey(token);
        return (idUsuario != null);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(2,1024,2,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable long id) {

        if(!validarToken(token)) {
            return;
        }

        usuarioDao.eliminar(id);
    }

}