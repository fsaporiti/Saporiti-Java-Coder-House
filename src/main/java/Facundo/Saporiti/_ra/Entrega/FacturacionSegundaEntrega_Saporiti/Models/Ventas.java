package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "VENTA")

public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate fechaVenta;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    @JsonIgnore
    private Cliente cliente;

    @OneToMany (mappedBy = "venta", cascade = CascadeType.ALL)
    private List<Ventas_Productos> ventasProductos;

    public Ventas() {
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setVentaProductos(List<Ventas_Productos> ventaProductos) {
        this.ventasProductos = ventaProductos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ventas_Productos> getVentasProductos() {
        return ventasProductos;
    }

    public void setVentasProductos(List<Ventas_Productos> ventasProductos) {
        this.ventasProductos = ventasProductos;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
