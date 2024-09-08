package Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Services;

import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Models.Productos;
import Facundo.Saporiti._ra.Entrega.FacturacionSegundaEntrega_Saporiti.Repositorios.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductosService {
    @Autowired
    private ProductosRepository productosRepository;

    public Productos agregarProducto(Productos productos) {
        return productosRepository.save(productos);
    }

    public Productos actualizarProducto(Productos productos) {
        return productosRepository.save(productos);
    }

    public Optional<Productos> buscarProductoById(Integer idProducto) {
        return productosRepository.findById(idProducto);
    }

    public void eliminarProducto(Integer idProducto) {
        productosRepository.deleteById(idProducto);
    }
}
