package io.allfns1Mercado.service;

import io.allfns1Mercado.domain.entity.Pedido;
import io.allfns1Mercado.domain.entity.enums.Statuspedido;
import io.allfns1Mercado.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

   Pedido salvar(PedidoDTO dto);

   Optional<Pedido> obterPedidoCompleto(Integer id);
   void atualizaStatus(Integer id, Statuspedido statuspedido);

}
