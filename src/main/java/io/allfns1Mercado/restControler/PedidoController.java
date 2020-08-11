package io.allfns1Mercado.restControler;


import io.allfns1Mercado.domain.entity.ItemPedido;
import io.allfns1Mercado.domain.entity.Pedido;
import io.allfns1Mercado.domain.entity.enums.Statuspedido;
import io.allfns1Mercado.dto.AtualizaStatusPedidoDTO;
import io.allfns1Mercado.dto.InformacaoItemPedidoDTO;
import io.allfns1Mercado.dto.InformacaoPedidoDTO;
import io.allfns1Mercado.dto.PedidoDTO;
import io.allfns1Mercado.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    private PedidoService service;


    public PedidoController(PedidoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody PedidoDTO dto){
       Pedido pedido =  this.service.salvar(dto);
       return pedido.getId();
    }


    @GetMapping("{id}")
    public InformacaoPedidoDTO getbyId(@PathVariable Integer id){

        return service.obterPedidoCompleto(id)
                .map(produto -> converter(produto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado"));
    }






    /*Atualizar o status do pedido*/
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  updatestaus(@PathVariable Integer id, @RequestBody AtualizaStatusPedidoDTO dto){

        String novoeStatus = dto.getNovostatus();
        service.atualizaStatus(id,Statuspedido.valueOf(novoeStatus));

    }


    private InformacaoPedidoDTO converter(Pedido pedido){

        return InformacaoPedidoDTO.builder()
                .codigo(pedido.getId())
                .cpf(pedido.getCliente().getCpf()).data(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens())).build();

    }


    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){

         if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
         }


         return  itens.stream().map(
                 item -> InformacaoItemPedidoDTO
                         .builder()
                         .descricaodoPedido(item.getProduto().getDescricao())
                         .precoUnitario(item.getProduto().getPreco())
                         .quantidade(item.getQuantidade()).build()).collect(Collectors.toList());


    }



}
