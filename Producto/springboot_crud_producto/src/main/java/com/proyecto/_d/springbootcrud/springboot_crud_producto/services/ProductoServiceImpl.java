package com.proyecto._d.springbootcrud.springboot_crud_producto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto._d.springbootcrud.springboot_crud_producto.controllers.ProductoRepository;
import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;


@Service
public class ProductoServiceImpl implements ProductoService{

@Autowired
    private ProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByAll() {
        return (List<Producto>) repository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    public Producto save(Producto productovar) {
        return repository.save(productovar);
    }
    @Override
    public Optional<Producto> delete(Producto productovar) {
        Optional<Producto> productoOptional = repository.findById(productovar.getId());
        productoOptional.ifPresent(productoBd ->{
            repository.delete(productovar);
        });
        return productoOptional;
        
    }
    @Override
    public Producto actualizarCantidad(Long id, Integer cantidad) {
        Optional<Producto> productoOptional = findById(id);
    if (productoOptional.isPresent()) {
        Producto producto = productoOptional.get();
        producto.setCantidad(producto.getCantidad() - cantidad);
        return save(producto);
    } else {
        throw new RuntimeException("Producto no encontrado");
    }
    }
}
