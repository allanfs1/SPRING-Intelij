package io.allfns1Mercado.service.imple;


import io.allfns1Mercado.Exception.PedidoNaoEncontradoException;
import io.allfns1Mercado.Exception.RegradeNegocioException;
import io.allfns1Mercado.domain.entity.Cliente;
import io.allfns1Mercado.domain.entity.ItemPedido;
import io.allfns1Mercado.domain.entity.Pedido;
import io.allfns1Mercado.domain.entity.Produto;
import io.allfns1Mercado.domain.entity.enums.Statuspedido;
import io.allfns1Mercado.domain.repositorio.Clientes;
import io.allfns1Mercado.domain.repositorio.ItensPedidos;
import io.allfns1Mercado.domain.repositorio.Pedidos;
import io.allfns1Mercado.domain.repositorio.Produtos;
import io.allfns1Mercado.dto.PedidoDTO;
import io.allfns1Mercado.dto.itemsPedidoDTO;
import io.allfns1Mercado.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoserviceImple  implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedidos ItemsPedidosrepository;

  /*
    public PedidoserviceImple(Pedidos repository, Clientes clientesRepository, Produtos produtosRepository) {
        this.repository = repository;
        this.clientesRepository = clientesRepository;
        this.produtosRepository = produtosRepository;
    }
   */





    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer Idncliente = dto.getCliente();
        Cliente cliente =  clientesRepository
                .findById(Idncliente)
                .orElseThrow(() -> new RegradeNegocioException("codigo do cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setCliente(cliente);
        pedido.setStatus(Statuspedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        ItemsPedidosrepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;

    }


    private List<ItemPedido> converterItems(Pedido pedido, List<itemsPedidoDTO> items){

        if(items.isEmpty()){
            throw  new RegradeNegocioException("Naõ é possivel realizar um pedido sem items");

        }

        return items.stream().map(dto -> {

                  Integer Idproduto = dto.getProduto();
                  Produto produto =  produtosRepository.findById(Idproduto)
                          .orElseThrow(() -> new RegradeNegocioException("Codigo de produto invalido" + Idproduto));


                  ItemPedido itemPedido = new ItemPedido();
                  itemPedido.setQuantidade(dto.getQuantidade());
                  itemPedido.setPedido(pedido);
                  itemPedido.setProduto(produto);
                  return itemPedido;

        }).collect(Collectors.toList());


    }


    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
         Optional<Pedido> op =   repository.findByIdFetchItens(id);
         return op;
    }



    /*Atualização via Patch*/
    @Override
    @Transactional
    public void atualizaStatus(Integer id, Statuspedido statuspedido) {

      repository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statuspedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());

    }


}
