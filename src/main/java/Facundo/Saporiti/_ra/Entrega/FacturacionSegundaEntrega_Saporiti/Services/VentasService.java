package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Services;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Cliente;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Productos;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Ventas;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Ventas_Productos;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios.ClienteRepository;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios.ProductosRepository;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios.VentasRepository;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios.Ventas_ProductosRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentasService {
    @Autowired
    private VentasRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductosRepository productoRepository;

    @Autowired
    private Ventas_ProductosRepository ventaProductoRepository;

    public Ventas RealizarVenta(Integer idCliente, Integer productoId, int cantidad) {

        Cliente cliente = clienteRepository.findById(Long.valueOf(idCliente))
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Productos producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Ventas venta = new Ventas();
        venta.setFechaVenta(LocalDate.now());
        venta.setCliente(cliente);
        venta =ventaRepository.save(venta);

        Ventas_Productos ventaProducto = new Ventas_Productos();
        ventaProducto.setVenta(venta);
        ventaProducto.setProducto(producto);
        ventaProducto.setCantidad(cantidad);
        ventaProductoRepository.save(ventaProducto);

        List<Ventas_Productos> ventaProductos = new ArrayList<>();
        ventaProductos.add(ventaProducto);
        venta.setVentaProductos(ventaProductos);

        return ventaRepository.save(venta);
    }

    public Optional<Ventas> buscarVentaById(Integer idVentas) {
        return ventaRepository.findById(idVentas);
    }

    public void eliminarVenta(Integer idVentas) {
        ventaRepository.deleteById(idVentas);
    }

    public Ventas actualizarVenta(Ventas ventas) {
        return ventaRepository.save(ventas);
    }

}