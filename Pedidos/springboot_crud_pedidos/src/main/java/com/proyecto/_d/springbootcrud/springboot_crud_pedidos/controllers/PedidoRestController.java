package com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.services.PedidoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {
    @Autowired
    private PedidoService service;

    @Operation(summary = "Listar todos los pedidos")
    @GetMapping
    public List<Pedido> listarPedidos(){
        return service.findByAll();
    }

    @Operation(summary = "Buscar pedido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verPedido (@PathVariable long id) {
        Optional<Pedido> pedidoOptional = service.findById(id);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build()
        ;
    }

    @Operation(summary = "Crear un nuevo pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
    try {
        if (pedido.getUsuarioId() == null || pedido.getProductos() == null || pedido.getProductos().isEmpty() || pedido.getFecha() == null) {
            throw new IllegalArgumentException("Datos inválidos");
        }
        Pedido nuevoPedido = service.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Modificar un pedido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> modificarPedido(@PathVariable Long id, @RequestBody Pedido pedidoEnviado) {
        Optional<Pedido> pedidoOptional = service.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedidoExistente = pedidoOptional.get();
            pedidoExistente.setUsuarioId(pedidoEnviado.getUsuarioId());
            pedidoExistente.setFecha(pedidoEnviado.getFecha());
            pedidoExistente.setTotal(pedidoEnviado.getTotal());
            pedidoExistente.setProductos(pedidoEnviado.getProductos());;

            return ResponseEntity.ok(service.save(pedidoExistente));
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Eliminar un pedido por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido eliminado"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = service.findById(id);
        if (pedidoOptional.isPresent()) {
            service.delete(pedidoOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

    
    
    
    
    

