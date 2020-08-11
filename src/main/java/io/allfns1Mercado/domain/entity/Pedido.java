package io.allfns1Mercado.domain.entity;

import io.allfns1Mercado.domain.entity.enums.Statuspedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name="pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="CLIENTE_ID")
    private Cliente cliente;

    @Column(name="total",precision = 20, scale = 2)
    private BigDecimal total;

    @Column(name="data_pedido")
    private LocalDate dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Statuspedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;



    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }



    public List<ItemPedido> getItemsList(){
        if(this.itens == null){
            return  this.itens = new ArrayList<>();
        }

          return  this.itens;
    }




}
