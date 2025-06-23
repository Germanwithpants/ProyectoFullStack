package com.proyecto._d.springbootcrud.springboot_crud_producto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.proyecto._d.springbootcrud.springboot_crud_producto.controllers.ProductoRepository;
import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;
import com.proyecto._d.springbootcrud.springboot_crud_producto.services.ProductoServiceImpl;
import com.proyecto._d.springbootcrud.springboot_crud_producto.testcontrollers.RepositoryFalse;

public class ProductoServiceTest {
    private final ProductoRepository repositorioFalso = new RepositoryFalse();
    private final ProductoServiceImpl productoService = new ProductoServiceImpl(repositorioFalso);

    @Test
    void findByAllTest() {
        List<Producto> productos = productoService.findByAll();
        assertEquals(2, productos.size());
    }

    @Test
    void findByIdTest() {
        Optional<Producto> producto = productoService.findById(1L);
        assertTrue(producto.isPresent());
        assertEquals("Producto 1", producto.get().getNombre());
    }

    @Test
    void saveTest() {
        Producto nuevo = new Producto(null, "Nuevo", "Nuevo desc", 999, 5, "Nueva");
        Producto guardado = productoService.save(nuevo);
        assertNotNull(guardado.getId());
        assertEquals("Nuevo", guardado.getNombre());
    }

    @Test
    void deleteExistenteTest() {
        Producto existente = new Producto(1L, "Producto 1", "Desc 1", 100, 10, "Cat 1");
        Optional<Producto> eliminado = productoService.delete(existente);
        assertTrue(eliminado.isPresent());
    }

    @Test
    void deleteInexistenteTest() {
        Producto inexistente = new Producto(99L, "Inexistente", "No existe", 0, 0, "N/A");
        Optional<Producto> eliminado = productoService.delete(inexistente);
        assertFalse(eliminado.isPresent());
    }

    @Test
    void actualizarCantidadExistenteTest() {
        Producto actualizado = productoService.actualizarCantidad(1L, 2);
        assertEquals(8, actualizado.getCantidad());
    }

    @Test
    void actualizarCantidadInexistenteTest() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productoService.actualizarCantidad(99L, 2);
        });
        assertEquals("Producto no encontrado", exception.getMessage());
    }

}
