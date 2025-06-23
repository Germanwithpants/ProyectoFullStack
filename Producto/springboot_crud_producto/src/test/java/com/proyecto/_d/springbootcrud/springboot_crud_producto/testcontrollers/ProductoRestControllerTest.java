package com.proyecto._d.springbootcrud.springboot_crud_producto.testcontrollers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto._d.springbootcrud.springboot_crud_producto.controllers.ProductoRestController;
import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;
import com.proyecto._d.springbootcrud.springboot_crud_producto.services.ProductoService;
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

@WebMvcTest(ProductoRestController.class)
public class ProductoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductosTest() throws Exception {
        List<Producto> productos = Arrays.asList(
                new Producto(1L, "Prod1", "Desc1", 100, 10, "Cat1"),
                new Producto(2L, "Prod2", "Desc2", 200, 5, "Cat2")
        );

        when(productoService.findByAll()).thenReturn(productos);

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void verProductoTest() throws Exception {
        Producto producto = new Producto(1L, "Prod1", "Desc1", 100, 10, "Cat1");

        when(productoService.findById(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Prod1"));
    }

    @Test
    void verProductoNoExisteTest() throws Exception {
        when(productoService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearProductoTest() throws Exception {
        Producto nuevo = new Producto(null, "Nuevo", "Desc", 300, 8, "Cat");
        Producto guardado = new Producto(3L, "Nuevo", "Desc", 300, 8, "Cat");

        when(productoService.save(any(Producto.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void modificarProductoTest() throws Exception {
        Producto existente = new Producto(1L, "Original", "Desc", 100, 10, "Cat");
        Producto modificado = new Producto(1L, "Modificado", "Desc2", 150, 5, "Cat2");

        when(productoService.findById(1L)).thenReturn(Optional.of(existente));
        when(productoService.save(any(Producto.class))).thenReturn(modificado);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Modificado"));
    }

    @Test
    void modificarProductoNoExisteTest() throws Exception {
        when(productoService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/productos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Producto())))
                .andExpect(status().isNotFound());
    }

    @Test
    void actualizarCantidadTest() throws Exception {
        Producto producto = new Producto(1L, "Prod1", "Desc", 100, 10, "Cat");

        when(productoService.actualizarCantidad(1L, 2)).thenReturn(producto);

        mockMvc.perform(put("/api/productos/1/cantidad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cantidad\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Prod1"));
    }

    @Test
    void eliminarProductoTest() throws Exception {
        Producto producto = new Producto(1L, "Prod1", "Desc", 100, 10, "Cat");

        when(productoService.delete(any(Producto.class))).thenReturn(Optional.of(producto));

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Prod1"));
    }

    @Test
    void eliminarProductoNoExisteTest() throws Exception {
        when(productoService.delete(any(Producto.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/productos/999"))
                .andExpect(status().isNotFound());
    }
}
