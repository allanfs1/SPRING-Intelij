package io.allfns1Mercado;


import io.allfns1Mercado.domain.entity.Cliente;
import io.allfns1Mercado.domain.entity.Produto;
import io.allfns1Mercado.domain.repositorio.Clientes;
import io.allfns1Mercado.domain.repositorio.Pedidos;
import io.allfns1Mercado.domain.repositorio.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.math.BigDecimal;


@SpringBootApplication
public class ApplicationMercado {



     @Bean
     public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos, @Autowired Produtos produtos){
         return args -> {
             /*Ciar clientes*/
             Cliente cli1 = new Cliente("Allan Ferreira de Souza ");
             clientes.save(cli1);

             Produto pro1 = new Produto();
             pro1.setDescricao("Arroz Camil");
             pro1.setPreco(new BigDecimal(10.00));
             produtos.save(pro1);


             /*
             Pedido pedi = new Pedido();
             pedi.setCliente(cli1);
             pedi.setTotal(BigDecimal.valueOf(100));
             pedi.setDataPedido(LocalDate.now());
             pedidos.save(pedi);

             Cliente cliente = clientes.ClientefindFich(cli1.getId());
             System.out.println(cliente);

             pedidos.findByCliente(cli1).forEach(System.out::println);
             */
             /*
             //Listar Todos os clientes
             List<Cliente> todos_cli = clientes.findAll();
             todos_cli.forEach(System.out::println);


             //Atualizar todos os clientes/
             todos_cli.forEach(cliente -> {
                 cliente.setNome(cliente.getNome()+"Atualiza");
                 clientes.save(cliente);

             });


             //Listar Todos os clientes/
             todos_cli = clientes.findAll();
             todos_cli.forEach(cli -> System.out.println(cli));
             */


             /*

             //Buscar por Nome do cliente/
             System.out.println("Buscar pelo nome");
             List<Cliente> result =  clientes.encontraPorLike("a");
             result.forEach(System.out::println);
            //------------------------------------------ ---------------

             boolean existi = clientes.existsByNome("Allan Ferreira de Souza Atualiza");
             System.out.println("Usuario existir "+existi);


             //Deletar usuario/
             System.out.println("Deletando cliente");

              todos_cli.forEach(cliente ->{
                  clientes.delete(cliente);

              });
            //System.out.println("Deletando cliente");
            //Cliente cli  = todos_cli.get(0);
            //clientes.deletar(cli);

              todos_cli = clientes.findAll();
              if(todos_cli.isEmpty()){
                  System.out.println("Não tem nenhum cliente");

              }

              else{
                  todos_cli.forEach(cli -> System.out.println(cli+"Remove"));
              }
              //---------------------------------
                  */
         };


     }


    public static void main(String[] args) {
        SpringApplication.run(ApplicationMercado.class,args);
    }

}
