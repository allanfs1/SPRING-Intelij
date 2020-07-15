package io.allfns1Mercado.restControler;


import io.allfns1Mercado.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    private PedidoService service;


    public PedidoController(PedidoService service) {
        this.service = service;
    }

}
