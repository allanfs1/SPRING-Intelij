package io.allfns1Mercado.domain.repositorio;


import io.allfns1Mercado.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface Clientes extends JpaRepository<Cliente,Integer> {

    @Query(value="SELECT * FROM Cliente as c where c.nome like %:nome%",nativeQuery = true)
    List<Cliente> encontraPorLikeNativo(@Param("nome") String nome);

    @Query(value="SELECT c FROM Cliente c where c.nome like :nome")
    List<Cliente> encontraPorLike(@Param("nome") String nome);

    /*Query Metodos*/
    List<Cliente> findByNomeLike(String nome);
    boolean existsByNome(String nome);

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);


    @Query("SELECT c FROM Cliente c LEFT JOIN  fetch  c.pedidos where c.id = :id")
    Cliente ClientefindFich(@Param("id") Integer id);



}
