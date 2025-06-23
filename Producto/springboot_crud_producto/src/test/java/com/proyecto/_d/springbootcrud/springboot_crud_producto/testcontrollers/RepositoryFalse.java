package com.proyecto._d.springbootcrud.springboot_crud_producto.testcontrollers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.proyecto._d.springbootcrud.springboot_crud_producto.controllers.ProductoRepository;
import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;

public class RepositoryFalse implements ProductoRepository{

    private final Map<Long, Producto> baseDatos = new HashMap<>();
    private long idSecuencia = 3;

    public RepositoryFalse() {
        baseDatos.put(1L, new Producto(1L, "Producto 1", "Desc 1", 100, 10, "Cat 1"));
        baseDatos.put(2L, new Producto(2L, "Producto 2", "Desc 2", 200, 5, "Cat 2"));
    }

    @Override
    public <S extends Producto> S save(S producto) {
        if (producto.getId() == null) {
            producto.setId(idSecuencia++);
        }
        baseDatos.put(producto.getId(), producto);
        return producto;
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(baseDatos.get(id));
    }

    @Override
    public Iterable<Producto> findAll() {
        return new ArrayList<>(baseDatos.values());
    }

    @Override
    public void delete(Producto producto) {
        baseDatos.remove(producto.getId());
    }

    // Métodos no usados, pero requeridos por CrudRepository (pueden dejarse sin implementar si no se usan)
    @Override public boolean existsById(Long id) { return false; }
    @Override public long count() { return 0; }
    @Override public void deleteById(Long id) {}
    @Override public void deleteAllById(Iterable<? extends Long> ids) {}
    @Override public void deleteAll(Iterable<? extends Producto> entities) {}
    @Override public void deleteAll() {}
    @Override public <S extends Producto> Iterable<S> saveAll(Iterable<S> entities) { return null; }
    @Override public Iterable<Producto> findAllById(Iterable<Long> ids) { return null; }
}
