package com.proyecto._d.springbootcrud.springboot_crud_producto.services;

import java.util.Optional;
import java.util.List;
import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;
public interface ProductoService {
    List<Producto> findByAll();
    
    Optional<Producto> findById(Long id);

    Producto save(Producto productovar);

    Optional<Producto> delete(Producto productovar);
    

    Producto actualizarCantidad(Long id, Integer cantidad);
}
