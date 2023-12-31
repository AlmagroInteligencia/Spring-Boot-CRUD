package com.racasoft.cursito.dao;

import com.racasoft.cursito.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios()
    {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    /*@Override
    public boolean verificarUsuario(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email AND password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .setParameter("password", usuario.getPassword())
                .getResultList();

        if(lista.isEmpty()) {
            return false;
        } else {
            return true;
        }

        // return !lista.isEmpty();
    }*/

    @Override
    public Usuario obtenerYVerificarUsuario(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> chaboncito = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (chaboncito.isEmpty()) {
            return null;
        }

        String hashedPass = chaboncito.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(hashedPass, usuario.getPassword())) {
            return chaboncito.get(0);
        }
        return null;
    }

}
