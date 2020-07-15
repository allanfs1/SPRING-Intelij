package io.allfns1Mercado.domain.repositorio;

import io.allfns1Mercado.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {

}
