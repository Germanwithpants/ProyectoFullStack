package com.proyecto._d.springbootcrud.springboot_crud.testcontrollers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto._d.springbootcrud.springboot_crud_Usuarios.controllers.UsuarioRestController;
import com.proyecto._d.springbootcrud.springboot_crud_Usuarios.entities.Usuario;
import com.proyecto._d.springbootcrud.springboot_crud_Usuarios.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioRestController.class)
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarUsuariosTest() throws Exception {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario(1L, "Felipe", "pass1", "pepito123@", "admin"),
                new Usuario(1L, "Nom2", "pass2", "pep3@", "logis"));


        when(usuarioService.findByAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void verUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(1L, "Felipe", "pass1", "pepito123@", "admin");

        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Prod1"));
    }

    @Test
    void verUsuarioNoExisteTest() throws Exception {
        when(usuarioService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearUsuarioTest() throws Exception {
        Usuario nuevo = new Usuario(2L, "Nuevo", "Pass", "arrobagmail", "admin");
        Usuario guardado = new Usuario(2L, "Nuevo", "Pass", "arrobagmail", "admin");

        when(usuarioService.save(any(Usuario.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void modificarUsuarioTest() throws Exception {
        Usuario existente = new Usuario(null, null, null, null, null);
        Usuario modificado = new Usuario(1L, "Modificado", "Desc2", "correo", "Cat2");

        when(usuarioService.findById(1L)).thenReturn(Optional.of(existente));
        when(usuarioService.save(any(Usuario.class))).thenReturn(modificado);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Modificado"));
    }

    @Test
    void modificarUsuarioNoExisteTest() throws Exception {
        when(usuarioService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/usuarios/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Usuario())))
                .andExpect(status().isNotFound());
    }
    @Test
    void eliminarUsuarioNoExisteTest() throws Exception {
        when(usuarioService.delete(any(Usuario.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/usuarios/999"))
                .andExpect(status().isNotFound());
    }
}
