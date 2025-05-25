package com.proyecto._d.springbootcrud.springboot_crud_pedidos.services;

import java.util.List;
import java.util.Optional;

import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;

public interface PedidoService {
    List<Pedido> findByAll();
    
    Optional<Pedido> findById(Long id);

    Pedido save(Pedido unPedido);

    Optional<Pedido> delete(Pedido unPedido);
}
