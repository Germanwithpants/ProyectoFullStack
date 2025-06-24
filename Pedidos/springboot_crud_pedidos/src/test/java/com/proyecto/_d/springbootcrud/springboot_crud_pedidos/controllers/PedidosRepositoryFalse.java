package com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.ProductoPedido;

public class PedidosRepositoryFalse implements PedidoRepository {

    private final Map<Long, Pedido> baseDatos = new HashMap<>();
    private long idSecuencia = 3;

    public PedidosRepositoryFalse() {
        List<ProductoPedido> productos1 = new ArrayList<>();
        productos1.add(new ProductoPedido());

        List<ProductoPedido> productos2 = new ArrayList<>();
        productos2.add(new ProductoPedido());

        baseDatos.put(1L, new Pedido(1L, 1L, productos1, java.sql.Date.valueOf("2022-01-05"), 100));
        baseDatos.put(2L, new Pedido(2L, 2L, productos2, java.sql.Date.valueOf("2022-03-05"), 150));
    }

    @Override
    public <S extends Pedido> S save(S Pedido) {
        if (Pedido.getId() == null) {
            Pedido.setId(idSecuencia++);
        }
        baseDatos.put(Pedido.getId(), Pedido);
        return Pedido;
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional.ofNullable(baseDatos.get(id));
    }

    @Override
    public Iterable<Pedido> findAll() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public void delete(Pedido Pedido) {
        baseDatos.remove(Pedido.getId());
    }

    // Métodos no usados, pero requeridos por CrudRepository (pueden dejarse sin implementar si no se usan)
    @Override public boolean existsById(Long id) { return false; }
    @Override public long count() { return 0; }
    @Override public void deleteById(Long id) {}
    @Override public void deleteAllById(Iterable<? extends Long> ids) {}
    @Override public void deleteAll(Iterable<? extends Pedido> entities) {}
    @Override public void deleteAll() {}
    @Override public <S extends Pedido> Iterable<S> saveAll(Iterable<S> entities) { return null; }
    @Override public Iterable<Pedido> findAllById(Iterable<Long> ids) { return null; }
}