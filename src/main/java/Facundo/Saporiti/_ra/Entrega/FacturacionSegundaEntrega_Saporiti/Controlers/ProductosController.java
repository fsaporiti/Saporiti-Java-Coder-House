package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Controlers;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Productos;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Services.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductosService productosService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getProductoById(@PathVariable(name = "id") Integer id) {
        Optional<Productos> productos = productosService.buscarProductoById(id);
        if (productos.isPresent()) {
            return ResponseEntity.ok(productos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> guardarProducto(@RequestBody Productos productos) {
        try {
            Productos productoGuardado = productosService.agregarProducto(productos);
            return ResponseEntity.created(URI.create("")).body(productoGuardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> actualizarProducto(@PathVariable(name = "id") Integer id, @RequestBody Productos productos) {
        try {
            Optional<Productos> productoExistente = productosService.buscarProductoById(id);
            if (productoExistente.isPresent()) {
                productos.setId(id);
                Productos productoActualizado = productosService.actualizarProducto(productos);
                return ResponseEntity.ok(productoActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable(name = "id") Integer id) {
        try {
            Optional<Productos> productoExistente = productosService.buscarProductoById(id);
            if (productoExistente.isPresent()) {
                productosService.eliminarProducto(id);
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
