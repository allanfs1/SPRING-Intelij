package io.allfns1Mercado.domain.repositorio;

import io.allfns1Mercado.domain.entity.Cliente;
import io.allfns1Mercado.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos  extends JpaRepository<Pedido,Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
