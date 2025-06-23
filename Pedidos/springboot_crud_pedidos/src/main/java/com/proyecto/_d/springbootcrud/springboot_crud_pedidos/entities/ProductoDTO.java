package com.proyecto._d.springbootcrud.springboot_crud_pedidos.entities;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String precio;
    private Integer cantidad;
    private String categoria;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoría(String categoría) {
        this.categoria = categoría;
    }
    public ProductoDTO(){

    }
}
