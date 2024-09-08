package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "VENTAS_PRODUCTOS")
public class Ventas_Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "VENTA_ID", insertable = false, updatable = false)
    @JsonIgnore
    private Ventas venta;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", insertable = false, updatable = false)
    private Productos producto;

    private Integer cantidad;

    public Ventas_Productos() {
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

}