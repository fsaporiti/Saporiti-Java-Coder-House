package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTO")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PRECIO")
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Ventas_Productos> ventasProductos;

    public Productos() {
    }

    public Productos(Cliente cliente, String nombre, String marca, BigDecimal precio, int stock) {
        this.cliente = cliente;
        this.nombre = nombre;
        this.precio = precio;

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ventas_Productos> getVentasProductos() {
        return ventasProductos;
    }

    public void setVentasProductos(List<Ventas_Productos> ventasProductos) {
        this.ventasProductos = ventasProductos;
    }

}
