package com.proyecto._d.springbootcrud.springboot_crud_usuario.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.proyecto._d.springbootcrud.springboot_crud_usuario.controllers.UsuarioRepository;
import com.proyecto._d.springbootcrud.springboot_crud_usuario.controllers.UsuarioRepositoryFalse;
import com.proyecto._d.springbootcrud.springboot_crud_usuario.entities.Usuario;


public class UsuarioServiceTest {
    private final UsuarioRepository repositorioFalso = new UsuarioRepositoryFalse();
    private final UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(repositorioFalso);

    @Test
    void findByAllTest() {
        List<Usuario> usuarios = usuarioService.findByAll();
        assertEquals(2, usuarios.size());
    }

    @Test
    void findByIdTest() {
        Optional<Usuario> usuario = usuarioService.findById(1L);
        assertTrue(usuario.isPresent());
        assertEquals("Usuario 1", usuario.get().getNombre());
    }

    @Test
    void saveTest() {
        Usuario nuevo = new Usuario(1L, "Felipe", "tumamaesbella", "dorito123@gmail.co", "Ricachon");
        Usuario guardado = usuarioService.save(nuevo);
        assertNotNull(guardado.getId());
        assertEquals("Felipe", guardado.getNombre());
    }

    @Test
    void deleteExistenteTest() {
        Usuario existente = new Usuario(1L, "Felipe", "tumamaesbella", "dorito123@gmail.co", "Ricachon");
        Optional<Usuario> eliminado = usuarioService.delete(existente);
        assertTrue(eliminado.isPresent());
    }

    @Test
    void deleteInexistenteTest() {
        Usuario inexistente = new Usuario(1L, "andres", "pee", "acetato@gmail.co", "pepe");
        Optional<Usuario> eliminado = usuarioService.delete(inexistente);
        assertTrue(eliminado.isPresent());
    }

}
