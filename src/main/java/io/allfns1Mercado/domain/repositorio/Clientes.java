package io.allfns1Mercado.domain.repositorio;


import io.allfns1Mercado.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes  {

    @Autowired
    private EntityManager entityManager;


    @Transactional
    public Cliente atualizar(Cliente cliente){
     this.entityManager.merge(cliente);
        return cliente;
    }



    @Transactional
    public void deletar(Cliente cliente){
        if(!this.entityManager.contains(cliente)){
            cliente  = this.entityManager.merge(cliente);
        }
        this.entityManager.remove(cliente);

    }


    @Transactional
    public void deletar(Integer id){
        Cliente cliente = this.entityManager.find(Cliente.class,id);
        this.deletar(cliente);
    }



    @Transactional
    public Cliente salvar (Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }


    @Transactional
    public List<Cliente> buscarPorNome(String nome){
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query =  entityManager.createQuery(jpql,Cliente.class);
        query.setParameter("nome","%"+nome+"%");
        return query.getResultList();
    }

    @Transactional
    public List<Cliente> obterTodos(){
      TypedQuery<Cliente>  query = this.entityManager.createQuery("from Cliente",Cliente.class);
      return query.getResultList();
    }





}
