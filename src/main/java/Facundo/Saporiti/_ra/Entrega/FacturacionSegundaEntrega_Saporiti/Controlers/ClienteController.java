package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Controlers;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Cliente;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public  ResponseEntity<?> getClienteById(@PathVariable(name = "id") Long id) {
        Optional<Cliente> cliente = clienteService.buscarClienteById(Math.toIntExact(id));
        if (cliente.isPresent()) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> agregarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteAgregado = clienteService.agregarCliente(cliente);
            return ResponseEntity.created(URI.create("")).body(clienteAgregado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> actualizarCliente(@PathVariable(name = "id") Long id, @RequestBody Cliente cliente) {
        try {
            Optional<Cliente> clienteExistente = clienteService.buscarClienteById(Math.toIntExact(id));
            if (clienteExistente.isPresent()) {
                cliente.setId(id);
                Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
                return ResponseEntity.ok(clienteActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable(name = "id") Integer id) {
        try {
            Optional<Cliente> clienteExistente = clienteService.buscarClienteById(id);
            if (clienteExistente.isPresent()) {
                clienteService.eliminarCliente(id);
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

