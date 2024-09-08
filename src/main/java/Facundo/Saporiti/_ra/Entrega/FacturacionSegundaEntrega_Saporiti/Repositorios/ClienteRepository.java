package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
