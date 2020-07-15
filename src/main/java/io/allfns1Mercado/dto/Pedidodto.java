package io.allfns1Mercado.dto;


import io.allfns1Mercado.domain.entity.ItemPedido;
import java.math.BigDecimal;
import java.util.List;

/**

 {
    "cliente":1,
    "total":10.0,
     "items":[
          {
            "produto":1,
            "quantidade":10

          }
            ]
 }


**/

public class Pedidodto {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedido> items;


}
