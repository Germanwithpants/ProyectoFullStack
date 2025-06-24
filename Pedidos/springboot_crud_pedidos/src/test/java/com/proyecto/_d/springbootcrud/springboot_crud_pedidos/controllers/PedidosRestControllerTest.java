package com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.ProductoPedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.services.PedidoService;


@WebMvcTest(PedidoRestController.class)
public class PedidosRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void listarPedidosTest() throws Exception {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido(1L, 1L, Arrays.asList(new ProductoPedido(1L, 1)), java.sql.Date.valueOf("2022-01-01"), 100),
                new Pedido(2L, 2L, Arrays.asList(new ProductoPedido(2L, 2)), java.sql.Date.valueOf("2022-01-15"), 200));

        when(pedidoService.findByAll()).thenReturn(pedidos);
        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void verPedidoTest() throws Exception {
        Pedido pedido = new Pedido(1L, 1L, Arrays.asList(new ProductoPedido(1L, 1)), java.sql.Date.valueOf("2022-01-01"), 100);

        when(pedidoService.findById(1L)).thenReturn(Optional.of(pedido));

        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void verPedidoNoExisteTest() throws Exception {
        when(pedidoService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pedidos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearPedidoTest() throws Exception {
        Pedido nuevo = new Pedido(1L, 1L, Arrays.asList(new ProductoPedido(1L, 1)), java.sql.Date.valueOf("2022-01-01"), 100);
        Pedido guardado = new Pedido(1L, 1L, Arrays.asList(new ProductoPedido(1L, 1)), java.sql.Date.valueOf("2022-01-01"), 100);

        when(pedidoService.save(any(Pedido.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

   
    @Test
    void crearPedidoConDatosInvalidosTest() throws Exception {
    Pedido nuevo = new Pedido();
    nuevo.setUsuarioId(null);
    nuevo.setProductos(null);
    nuevo.setFecha(null);
    
    mockMvc.perform(post("/api/pedidos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(nuevo)))
            .andExpect(status().isBadRequest());
}

    @Test
    void modificarPedidoTest() throws Exception {
        Pedido existente = new Pedido(1L, 1L, Arrays.asList(new ProductoPedido(1L, 1)), java.sql.Date.valueOf("2022-01-01"), 100);
        Pedido modificado = new Pedido(1L, 1L, Arrays.asList(new ProductoPedido(1L, 2)), java.sql.Date.valueOf("2022-01-01"), 200);

        when(pedidoService.findById(1L)).thenReturn(Optional.of(existente));
        when(pedidoService.save(any(Pedido.class))).thenReturn(modificado);

        mockMvc.perform(put("/api/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modificado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void modificarPedidoNoExisteTest() throws Exception {
        when(pedidoService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/pedidos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Pedido())))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarPedidoExistenteTest() throws Exception {
    Pedido pedido = new Pedido();
    pedido.setId(1L);
    
    when(pedidoService.findById(1L)).thenReturn(Optional.of(pedido));
    
    mockMvc.perform(delete("/api/pedidos/1"))
        .andExpect(status().isOk());
}

    @Test
    void eliminarPedidoNoExisteTest() throws Exception {
        when(pedidoService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/pedidos/999"))
                .andExpect(status().isNotFound());
    }
}


