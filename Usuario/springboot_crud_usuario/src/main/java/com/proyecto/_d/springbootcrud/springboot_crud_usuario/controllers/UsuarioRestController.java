package com.proyecto._d.springbootcrud.springboot_crud_usuario.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto._d.springbootcrud.springboot_crud_usuario.entities.Usuario;
import com.proyecto._d.springbootcrud.springboot_crud_usuario.services.UsuarioService;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verUsuario (@PathVariable long id){
        Optional<Usuario> usuarioOptional = service.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario unUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario unUsuario) {
        Optional <Usuario> usuarioOptional= service.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioexist = usuarioOptional.get();
            usuarioexist.setNombre(unUsuario.getNombre());
            usuarioexist.setCorreo(unUsuario.getCorreo());
            usuarioexist.setRol(unUsuario.getRol());
            Usuario usuariobrandnew = service.save(usuarioexist);
            return ResponseEntity.ok(usuariobrandnew);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar (@PathVariable Long id){
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional<Usuario> usuarioOptional = service.delete(unUsuario);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    }
