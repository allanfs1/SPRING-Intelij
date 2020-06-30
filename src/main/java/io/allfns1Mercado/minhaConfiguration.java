package io.allfns1Mercado;


import io.allfns1Mercado.repository.ClienteRepository;
import io.allfns1Mercado.service.MercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class minhaConfiguration {





    @Bean
    public CommandLineRunner exultar(){
        return args -> {
          System.out.println("Init Configuracao");
        };

    }

    @Bean(name="NomeApp")
    public  String nomeApp(){
          return "aplicacao Nova";
    }

    @Bean(name="PagName")
    public  String PagName(){
        return "Pagina Nova";
    }


    @Bean(name="salvarmet")
    public  void  salvarmet(){

    }






}
