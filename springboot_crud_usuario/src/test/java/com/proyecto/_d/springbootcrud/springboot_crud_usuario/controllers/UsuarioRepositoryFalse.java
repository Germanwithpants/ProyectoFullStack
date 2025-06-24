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
        
        baseDatos.put(1L, new Usuario(1L, "Usuario 1", "Desc 1", "100",  "Cat 1"));
        baseDatos.put(2L, new Usuario(2L, "Usuario 2", "Desc 2", "200", "Cat 2"));
    }

    @Override
    public <S extends Usuario> S save(S Usuario) {
        if (Usuario.getId() == null) {
            Usuario.setId(idSecuencia++);
        }
        baseDatos.put(Usuario.getId(), Usuario);
        return Usuario;
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
    public void delete(Usuario Usuario) {
        baseDatos.remove(Usuario.getId());
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
