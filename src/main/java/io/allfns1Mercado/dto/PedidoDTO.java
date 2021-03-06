package io.allfns1Mercado.dto;


import io.allfns1Mercado.domain.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private List<itemsPedidoDTO> items;


}
