package io.allfns1Mercado.domain.repositorio;


import io.allfns1Mercado.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select *  from cliente";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete  from cliente where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    public Cliente atualizar(Cliente cliente){
        this.jdbcTemplate.update(UPDATE,new Object[]{cliente.getNome(),cliente.getId()});
        return cliente;
    }


    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    @Transactional
    public Cliente salvar (Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }



    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ? "),
                new Object[]{"%"+nome+"%"},ObterMapper());
    }


    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, ObterMapper());
    }



    private RowMapper<Cliente> ObterMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id,nome);

            }
        };
    }


}
