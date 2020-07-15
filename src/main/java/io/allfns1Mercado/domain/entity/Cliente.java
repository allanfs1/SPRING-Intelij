package io.allfns1Mercado.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="Cliente")
//@Table(name="tb_cliente",schema="vendas")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;

    @Column (name = "nome", length = 100)
    private String nome;

    @Column(name="cpf", length = 11)
    private String cpf;


    @JsonIgnore
    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;




    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }
}
