package com.proyecto._d.springbootcrud.springboot_crud_producto.controllers;

import org.springframework.data.repository.CrudRepository;

import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto,Long>{
}
