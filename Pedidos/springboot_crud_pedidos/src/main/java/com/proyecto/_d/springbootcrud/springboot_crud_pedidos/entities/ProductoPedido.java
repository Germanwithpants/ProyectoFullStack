package com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoPedido {
        private Long idProducto;
        private Integer cantidad;
        public Long getIdProducto() {
            return idProducto;
        }
        public void setIdProducto(Long idProducto) {
            this.idProducto = idProducto;
        }
        public Integer getCantidad() {
            return cantidad;
        }
        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
        public ProductoPedido(Long idProducto, Integer cantidad) {
            this.idProducto = idProducto;
            this.cantidad = cantidad;
        }
        public ProductoPedido(){

        }
    
}
