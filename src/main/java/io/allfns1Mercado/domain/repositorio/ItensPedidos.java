package io.allfns1Mercado.domain.repositorio;

import io.allfns1Mercado.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidos  extends JpaRepository<ItemPedido,Integer> {

}
