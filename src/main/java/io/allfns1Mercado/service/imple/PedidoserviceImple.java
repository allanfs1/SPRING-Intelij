package io.allfns1Mercado.service.imple;


import io.allfns1Mercado.domain.repositorio.Pedidos;
import io.allfns1Mercado.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoserviceImple  implements PedidoService {

    private Pedidos repository;

    public PedidoserviceImple(Pedidos repository) {
        this.repository = repository;
    }
}
