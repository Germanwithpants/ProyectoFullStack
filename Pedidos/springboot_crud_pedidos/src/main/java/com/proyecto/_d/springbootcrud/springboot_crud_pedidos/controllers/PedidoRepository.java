package com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers;

import org.springframework.data.repository.CrudRepository;

import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
