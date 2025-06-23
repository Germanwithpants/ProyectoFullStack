package com.proyecto._d.springbootcrud.springboot_crud_ventas.controllers;

import org.springframework.data.repository.CrudRepository;

import com.proyecto._d.springbootcrud.springboot_crud_ventas.entities.Venta;

public interface VentaRepository extends CrudRepository<Venta,Long>{

}
