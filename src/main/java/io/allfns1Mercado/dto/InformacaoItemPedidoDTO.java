package io.allfns1Mercado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {

    private String descricaodoPedido;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
