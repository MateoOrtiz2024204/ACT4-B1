package com.mateoortiz.repuestosAutomotrices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Repuestos")
public class Repuestos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Integer idRepuesto;

    @Column(name = "nombre_repuesto")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreRepuesto;

    @Column(name = "categoria_repuesto")
    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoriaRepuesto;

    @Column(name = "precio_compra")
    @NotNull(message = "El precio de compra no puede estar vacío")
    @DecimalMin(value = "0.01", message = "El precio de compra debe ser mayor a 0")
    private Double precioCompra;

    @Column(name = "precio_venta")
    @NotNull(message = "El precio de venta no puede estar vacío")
    @DecimalMin(value = "0.01", message = "El precio de venta debe ser mayor a 0")
    private Double precioVenta;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @NotNull(message = "El proveedor no puede estar vacío")
    private Proveedor proveedor;

    // Getters y Setters
    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public String getCategoriaRepuesto() {
        return categoriaRepuesto;
    }

    public void setCategoriaRepuesto(String categoriaRepuesto) {
        this.categoriaRepuesto = categoriaRepuesto;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}