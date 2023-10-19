package com.racasoft.cursito.dao;

import com.racasoft.cursito.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(long id);

    void registrar(Usuario usuario);

    Usuario obtenerYVerificarUsuario(Usuario usuario);
}
