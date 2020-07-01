package io.allfns1Mercado;


import io.allfns1Mercado.domain.entity.Cliente;
import io.allfns1Mercado.domain.repositorio.Clientes;
import io.allfns1Mercado.service.MercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
public class ApplicationMercado {



     @Bean
     public CommandLineRunner init(@Autowired Clientes clientes){
         return args -> {
             /*Ciar clientes*/
             clientes.salvar(new Cliente("Allan Ferreira de Souza "));
             clientes.salvar(new Cliente("Eduardo do Santos Pereira SoMoza"));

             /*Listar Todos os clientes*/
             List<Cliente> todos_cli = clientes.obterTodos();
             todos_cli.forEach(System.out::println);


             //Atualizar todos os clientes/
             todos_cli.forEach(cliente -> {
                 cliente.setNome(cliente.getNome()+"Atualiza");
                 clientes.atualizar(cliente);

             });

             //Listar Todos os clientes/
             todos_cli = clientes.obterTodos();
             todos_cli.forEach(cli -> System.out.println(cli));

             //Buscar por Nome do cliente/
             System.out.println("Buscar pelo nome");
             todos_cli =  clientes.buscarPorNome("a");
             todos_cli.forEach(cli -> System.out.println(cli));
            //---------------------------------------------------------


             //Deletar usuario/
             System.out.println("Deletando cliente");
              todos_cli.forEach(cliente ->{
                  clientes.deletar(cliente);

              });

              todos_cli = clientes.obterTodos();
              if(todos_cli.isEmpty()){
                  System.out.println("Não tem nenhum cliente");

              }

              else{
                  todos_cli.forEach(cli -> System.out.println(cli+"Remove"));
              }
              //---------------------------------

         };

     }


    public static void main(String[] args) {
        SpringApplication.run(ApplicationMercado.class,args);
    }

}
