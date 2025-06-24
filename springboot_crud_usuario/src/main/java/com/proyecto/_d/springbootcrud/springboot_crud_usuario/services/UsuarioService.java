package com.proyecto._d.springbootcrud.springboot_crud_usuario.services;


import java.util.List;
import java.util.Optional;

import com.proyecto._d.springbootcrud.springboot_crud_usuario.entities.Usuario;

public interface UsuarioService {
    List<Usuario> findByAll();
    
    Optional<Usuario> findById(Long id);

    Usuario save(Usuario unUsuario);

    Optional<Usuario> delete(Usuario unUsuario);
    
}
