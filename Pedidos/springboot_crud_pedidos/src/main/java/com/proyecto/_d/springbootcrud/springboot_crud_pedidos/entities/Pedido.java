package com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuarioId;
    @ElementCollection
    @CollectionTable(
        name = "pedido_productos",
        joinColumns = @JoinColumn(name = "pedido_id")
    )
    private List<ProductoPedido> productos; 
    private Date fecha;
    private int total;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public List<ProductoPedido> getProductos() {
        return productos;
    }
    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Pedido(Long id, Long usuarioId, List<ProductoPedido> productos, Date fecha, int total) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.productos = productos;
        this.fecha = fecha;
        this.total = total;
    }
    public Pedido(){

    }
    
    
}

