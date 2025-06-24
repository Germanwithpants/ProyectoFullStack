package com.proyecto._d.springbootcrud.springboot_crud_producto.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto._d.springbootcrud.springboot_crud_producto.entities.Producto;
import com.proyecto._d.springbootcrud.springboot_crud_producto.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
    @Autowired
    private ProductoService service;
    
    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", description = "Listado de productos obtenido correctamente")
    @GetMapping
    public List<Producto> listarProductos() {
        return service.findByAll();
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verProducto(@PathVariable long id) {
        Optional<Producto> productoOptional = service.findById(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @PostMapping()
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto productovar) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productovar));
    }

    @Operation(summary = "Modificar un producto existente por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificarProducto(@PathVariable Long id, @RequestBody Producto productovar) {
        Optional<Producto> productoOptional = service.findById(id);
        if (productoOptional.isPresent()) {
            Producto productoexist = productoOptional.get();
            productoexist.setNombre(productovar.getNombre());
            productoexist.setDescripcion(productovar.getDescripcion());
            productoexist.setPrecio(productovar.getPrecio());
            productoexist.setCantidad(productovar.getCantidad());
            productoexist.setCategoría(productovar.getCategoria());
            Producto productobrandnew = service.save(productoexist);
            return ResponseEntity.ok(productobrandnew);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar solo la cantidad del producto")
    @ApiResponse(responseCode = "200", description = "Cantidad actualizada correctamente")
    @PutMapping("/{id}/cantidad")
    public ResponseEntity<Producto> actualizarCantidad(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
    Producto producto = service.actualizarCantidad(id, body.get("cantidad"));
    return ResponseEntity.ok(producto);
}

    @Operation(summary = "Eliminar un producto por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Producto productovar = new Producto();
        productovar.setId(id);
        Optional<Producto> productoOptional = service.delete(productovar);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}

