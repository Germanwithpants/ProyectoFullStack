package com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoid;
    private Long productoid;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPedidoid() {
        return pedidoid;
    }
    public void setPedidoid(Long pedidoid) {
        this.pedidoid = pedidoid;
    }
    public Long getProductoid() {
        return productoid;
    }
    public void setProductoid(Long productoid) {
        this.productoid = productoid;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public PedidoDetalle(Long id, Long pedidoid, Long productoid, Integer cantidad, BigDecimal precioUnitario) {
        this.id = id;
        this.pedidoid = pedidoid;
        this.productoid = productoid;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    public PedidoDetalle(){
        
    }
    
}
