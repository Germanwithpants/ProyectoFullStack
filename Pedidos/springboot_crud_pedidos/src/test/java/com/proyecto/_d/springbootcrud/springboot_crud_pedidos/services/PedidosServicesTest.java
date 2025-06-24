package com.proyecto._d.springbootcrud.springboot_crud_pedidos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers.PedidoRepository;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers.PedidosRepositoryFalse;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.ProductoPedido;



public class PedidosServicesTest {
    private final PedidoRepository repositoriofalso = new PedidosRepositoryFalse();
    private final PedidoServiceImpl pedidoService = new PedidoServiceImpl(repositoriofalso);

    @Test
    void findByAllTest() {
        List<Pedido> pedidos = pedidoService.findByAll();
        assertEquals(2, pedidos.size());
    }

    @Test
    void findByIdTest() {
        Optional<Pedido> pedido = pedidoService.findById(1L);
        assertTrue(pedido.isPresent());
    }

    @Test
    void saveTest() {
        List<ProductoPedido> productos = new ArrayList<>();
        Pedido nuevo = new Pedido(1L, 1L, productos, java.sql.Date.valueOf("2022-02-01"), 100);
        Pedido guardado = pedidoService.save(nuevo);
        assertNotNull(guardado.getId());
    }

    @Test
    void deleteExistenteTest() {
        List<ProductoPedido> productos = new ArrayList<>();
        Pedido existente = new Pedido(1L, 1L, productos, java.sql.Date.valueOf("2022-03-01"), 150);
        Optional<Pedido> eliminado = pedidoService.delete(existente);
        assertTrue(eliminado.isPresent());
    }

    @Test
    void deleteInexistenteTest() {
        List<ProductoPedido> productos = new ArrayList<>();
        Pedido inexistente = new Pedido(100L, 1L, productos, java.sql.Date.valueOf("2022-03-01"), 200);
        Optional<Pedido> eliminado = pedidoService.delete(inexistente);
        assertFalse(eliminado.isPresent()); 
    }
}