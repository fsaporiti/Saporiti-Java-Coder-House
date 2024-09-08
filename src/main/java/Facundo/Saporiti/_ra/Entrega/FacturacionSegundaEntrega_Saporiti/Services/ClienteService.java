package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Services;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Cliente;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarClienteById(Integer idCliente) {
        return clienteRepository.findById(Long.valueOf(idCliente));
    }

    public void eliminarCliente(Integer idCliente) {
        clienteRepository.deleteById(Long.valueOf(idCliente));
    }
}
