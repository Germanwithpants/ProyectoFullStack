package com.proyecto._d.springbootcrud.springboot_crud_pedidos.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.proyecto._d.springbootcrud.springboot_crud_pedidos.controllers.PedidoRepository;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.Pedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.ProductoPedido;
import com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities.UsuarioDTO;


@Service
public class PedidoServiceImpl implements PedidoService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PedidoRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Pedido> findByAll() {
        return (List<Pedido>) repository.findAll();
    }
    @Override
    @Transactional(readOnly=true)
    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }
    

    public Pedido save(Pedido unPedido) {
    if (unPedido.getUsuarioId() == null) {
        throw new RuntimeException("El id del usuario es requerido");
    }

    ResponseEntity<UsuarioDTO> response = restTemplate.getForEntity(
        "http://localhost:8080/api/usuarios/" + unPedido.getUsuarioId(),
        UsuarioDTO.class
    );

    if (!response.getStatusCode().is2xxSuccessful()) {
        throw new RuntimeException("Usuario no encontrado");
    }

    for (ProductoPedido producto : unPedido.getProductos()) {
        if (producto.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad del producto debe ser mayor que cero");
        }
        restTemplate.put("http://localhost:8081/api/productos/" + producto.getIdProducto() + "/cantidad", Map.of("cantidad", producto.getCantidad()));
    }

    return repository.save(unPedido);
}
    @Override
    public Optional<Pedido> delete(Pedido unPedido) {
        Optional<Pedido> pedidoOptional = repository.findById(unPedido.getId());
        pedidoOptional.ifPresent(pedidoDb ->{
            repository.delete(unPedido);
        });
        return pedidoOptional;
    }


}
