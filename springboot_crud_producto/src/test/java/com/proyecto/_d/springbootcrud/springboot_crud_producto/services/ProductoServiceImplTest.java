package com.proyecto._d.springbootcrud.springboot_crud_producto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto._d.springbootcrud.springboot_crud_producto.controllers.ProductoRepository;
import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;
public class ProductoServiceImplTest {
    @InjectMocks
    private ProductoServiceImpl service;
    @Mock
    private ProductoRepository repository;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeproducto();
    }
    List<Producto> list = new ArrayList<>();
    @Test
    public void findByAllTest(){
        when(repository.findAll()).thenReturn(list);
        List<Producto> reponse = service.findByAll();
        
        assertEquals(3, reponse.size());

        verify(repository, times(1)).findAll();
    }
    public void chargeproducto(){
        Producto prod1 = new Producto(Long.valueOf(1), "galletas", "una galleta de chocolate", 100, 5, "comida");
        Producto prod2= new Producto(Long.valueOf(2), "macarenas", "una macarena de chocolate", 100, 5, "comida");
        Producto prod3 = new Producto(Long.valueOf(3), "brownie", "un brownie de chocolate", 100, 5, "comida");

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }
    @Test
void testFindById_Existente() {
    Producto producto = new Producto(Long.valueOf(0), "B", null, 0, null, null);
    when(repository.findById(1L)).thenReturn(Optional.of(producto));

    Optional<Producto> result = service.findById(1L);

    assertTrue(result.isPresent());
    assertEquals("B", result.get().getNombre());
}

}   
