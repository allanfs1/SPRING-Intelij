package io.allfns1Mercado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.allfns1Mercado.repository.ClienteRepository;
@Service
public class MercadoService {


    private ClienteRepository repository;

    @Autowired
    MercadoService(ClienteRepository repository){
        this.repository = repository;
    }


    public String estoque(){
        return "estoque do mercado";
    }

    public String getTamanho(){
        return " Tamanho do  mercado";
    }


    public void salvar(){
        if(this.validarFornecedor("Allan") == false) {
            this.repository.InsertFornecedor();
            System.out.println("Validadcao ok");
        }
    }

    public boolean validarFornecedor(String nome){
           boolean valida = false;
           if(nome == "Allan"){
               valida = true;
           }

             return valida;

    }





}
