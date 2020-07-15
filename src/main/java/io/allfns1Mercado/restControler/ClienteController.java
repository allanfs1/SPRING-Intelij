package io.allfns1Mercado.restControler;


import io.allfns1Mercado.domain.entity.Cliente;
import io.allfns1Mercado.domain.entity.Produto;
import io.allfns1Mercado.domain.repositorio.Clientes;
import io.allfns1Mercado.domain.repositorio.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/cliente")
public class ClienteController {

     private Clientes clientes;
     private Produtos repository;

    public ClienteController(Clientes clientes, Produtos repository){
         this.clientes = clientes;
         this.repository = repository;
     }




    @RequestMapping(value="{nome}",method = RequestMethod.GET)
    public String  olaMundo (@PathVariable("nome") String nomeCliente){
        return String.format("Ola %s ",nomeCliente);
    }


    @GetMapping("cod/{codigoCliente}")
    public ResponseEntity<Cliente> getInd(@PathVariable("codigoCliente") Integer id){

        Optional<Cliente> cliente  =  clientes.findById(id);
        // ResponseEntity<Cliente> cli;

        if(cliente.isPresent()){
            //return cli = new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();

    }




    @GetMapping("findId/{codigo}")
    public Cliente findInd(@PathVariable("codigo") Integer id){

       return clientes.
               findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));


    }





    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente saveCli(@RequestBody Cliente cliente){

         Cliente cli =  clientes.save(cliente);
         return cli;
        //return ResponseEntity.ok(cli);


    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        Optional<Cliente> cli = clientes.findById(id);

        if (cli.isPresent()) {
            clientes.delete(cli.get());
            return ResponseEntity.noContent().build();
        }
        else {

           return  ResponseEntity.notFound().build();
        }




    }


    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id , @RequestBody Cliente cliente){

         Optional<Cliente> cli  = clientes.findById(id);

         return  cli.map(clienteExistente -> {
             cliente.setId(clienteExistente.getId());
             clientes.save(cliente);
             return ResponseEntity.noContent().build();

         }).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PutMapping("/updateNovo/{id}")
    public ResponseEntity updateNovo(@PathVariable Integer id , @RequestBody Cliente cliente){

        Optional<Cliente> cli  = clientes.findById(id);


        if(cli.isPresent()){

            cliente.setId(cli.get().getId());
            clientes.save(cliente);

            return ResponseEntity.noContent().build();
        }


        else{
            return ResponseEntity.notFound().build();
        }


    }


    @GetMapping("/filtro")
    public  ResponseEntity findMethod(Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher
                                  .matching()
                                  .withIgnoreCase().
                                   withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro,matcher);

        List<Cliente> lista = clientes.findAll(example);

        return ResponseEntity.ok(lista);

    }
//0-----------------------------------------------------------------------------------------
/*
    @PostMapping("produto/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarProduto(@RequestBody Produto produto){

        Produto p;

        try {
            p = repository.save(produto);
        }

        catch (Exception e){
            ResponseEntity.notFound().build();
        }


    }
*/



 /*
    @GetMapping("produto/{id}")
public ResponseEntity<Produto> getIdProduto(@PathVariable("id") Integer id){


    Optional<Produto> produto =  produtos.findById(id);
    ResponseEntity <Produto> prod;

    if(produto.isPresent()){

        prod = new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        return prod;
    }

    return ResponseEntity.notFound().build();


}


@PostMapping("produto")
@ResponseStatus(HttpStatus.CREATED)
public void salvarProduto(@RequestBody Produto produto){

        Produto p;

        try {
            p = produtos.save(produto);
        }

       catch (Exception e){
            ResponseEntity.notFound().build();
       }


}



@DeleteMapping("{id}")
   public void  deleteProduto(@PathVariable("id") Integer id){

      Optional<Produto> pro  = produtos.findById(id);

      try{
          if(pro.isPresent()) {
              produtos.delete(pro.get());
          }
      }
      catch (Exception e){
           System.out.println("Erro ao deletar");
      }



}



@PutMapping("put/{id}")
    public ResponseEntity updateProduto(@RequestBody Produto produto, @PathVariable("id") Integer id){

    Optional<Produto> find = produtos.findById(id);

    return find.map(produtofind -> {

        produto.setId(produtofind.getId());
        produtos.save(produto);
        return ResponseEntity.noContent().build();

    }).orElseGet(() -> ResponseEntity.notFound().build());


}
*/


}
