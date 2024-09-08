package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Controlers;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Ventas;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Ventas_Productos;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Services.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/venta")
public class VentasController {
    @Autowired
    private VentasService ventasService;

    @PostMapping(value = "/crear/{clienteId}")
    public Ventas RealizarVenta(
            @PathVariable Integer clienteId,
            @RequestBody Ventas_Productos ventaProducto) {
        return ventasService.RealizarVenta(clienteId, ventaProducto.getProducto().getId(), ventaProducto.getCantidad());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getVentaById(@PathVariable(name = "id") Integer id) {
        Optional<Ventas> ventas = ventasService.buscarVentaById(id);
        if (ventas.isPresent()) {
            return ResponseEntity.ok(ventas.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> actualizarVenta(@PathVariable(name = "id") Integer id, @RequestBody Ventas ventas) {
        try {
            Optional<Ventas> ventaExistente = ventasService.buscarVentaById(id);
            if (ventaExistente.isPresent()) {
                ventas.setId(id); // Asegúrate de que el ID se mantenga igual
                Ventas ventaActualizada = ventasService.actualizarVenta(ventas);
                return ResponseEntity.ok(ventaActualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable(name = "id") Integer id) {
        try {
            Optional<Ventas> ventaExistente = ventasService.buscarVentaById(id);
            if (ventaExistente.isPresent()) {
                ventasService.eliminarVenta(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}