package com.proyecto._d.springbootcrud.springboot_crud_usuario.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.proyecto._d.springbootcrud.springboot_crud_usuario.entities.Usuario;



public class UsuarioRepositoryFalse implements UsuarioRepository{

        private final Map<Long, Usuario> baseDatos = new HashMap<>();
    private long idSecuencia = 3;

    public UsuarioRepositoryFalse() {
        baseDatos.put(1L, new Usuario(1L, "Usuario 1", "Pass 1",  "Correo 1", "Admint 1"));
        baseDatos.put(1L, new Usuario(1L, "Usuario 2", "Pass 2",  "Correo 2", "Logistic 1"));
    }
    @Override
    public <S extends Usuario> S save(S usuario) {
        if (usuario.getId() == null) {
            usuario.setId(idSecuencia++);
        }
        baseDatos.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(baseDatos.get(id));
    }

    @Override
    public Iterable<Usuario> findAll() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public void delete(Usuario usuario) {
        baseDatos.remove(usuario.getId());
    }

    // Métodos no usados, pero requeridos por CrudRepository (pueden dejarse sin implementar si no se usan)
    @Override public boolean existsById(Long id) { return false; }
    @Override public long count() { return 0; }
    @Override public void deleteById(Long id) {}
    @Override public void deleteAllById(Iterable<? extends Long> ids) {}
    @Override public void deleteAll(Iterable<? extends Usuario> entities) {}
    @Override public void deleteAll() {}
    @Override public <S extends Usuario> Iterable<S> saveAll(Iterable<S> entities) { return null; }
    @Override public Iterable<Usuario> findAllById(Iterable<Long> ids) { return null; }
}
