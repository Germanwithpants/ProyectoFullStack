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




@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {
    @Autowired
    private PedidoService service;


    @GetMapping
    public List<Pedido> listarPedidos(){
        return service.findByAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> verPedido (@PathVariable long id) {
        Optional<Pedido> pedidoOptional = service.findById(id);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build()
        ;
    }
    @PostMapping()
    public ResponseEntity<Pedido> crearPedido (@RequestBody Pedido unPedido){
    System.out.println("Pedido recibido: " + unPedido);
    System.out.println("Usuario ID: " + unPedido.getUsuarioId());
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unPedido));
}
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

    
    
    
    
    

