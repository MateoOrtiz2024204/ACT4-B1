package com.mateoortiz.repuestosAutomotrices.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @Column(name = "cantidad")
    private Integer cantidadRepuesto;

    @Column(name = "total")
    private Double totalRepuesto;

    //-------------------
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_repuesto", nullable = false)
    private Repuestos repuestos;
    //-------------------

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCantidadRepuesto() {
        return cantidadRepuesto;
    }

    public void setCantidadRepuesto(Integer cantidadRepuesto) {
        this.cantidadRepuesto = cantidadRepuesto;
    }

    public Integer getTotalRepuesto() {
        return totalRepuesto;
    }

    public void setTotalRepuesto(Integer totalRepuesto) {
        this.totalRepuesto = totalRepuesto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Repuestos getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Repuestos repuestos) {
        this.repuestos = repuestos;
    }
}
