package com.proyecto._d.springbootcrud.springboot_crud_Usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto._d.springbootcrud.springboot_crud_Usuarios.controllers.UsuarioRepository;
import com.proyecto._d.springbootcrud.springboot_crud_Usuarios.entities.Usuario;
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByAll() {
        return (List<Usuario>) repository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Usuario save(Usuario unUsuario) {
        return repository.save(unUsuario);
    }
    @Override
    public Optional<Usuario> delete(Usuario unUsuario) {
        Optional<Usuario> usuarioOptional = repository.findById(unUsuario.getId());
        usuarioOptional.ifPresent(usuarioDb ->{
            repository.delete(unUsuario);
        });
        return usuarioOptional;
    }


    
}
