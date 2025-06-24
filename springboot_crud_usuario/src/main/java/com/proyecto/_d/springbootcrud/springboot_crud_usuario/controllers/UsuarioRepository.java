package com.proyecto._d.springbootcrud.springboot_crud_usuario.controllers;

import org.springframework.data.repository.CrudRepository;

import com.proyecto._d.springbootcrud.springboot_crud_usuario.entities.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {}

