package com.jego.tareabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleVenta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idVenta", nullable = false, foreignKey = @ForeignKey(name = "FK_venta_detalle"))
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false, foreignKey = @ForeignKey(name = "FK_producto_detalle"))
    private Producto producto;

    @Min(value = 0)
    private double cantidad;

    public Integer getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Integer idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
